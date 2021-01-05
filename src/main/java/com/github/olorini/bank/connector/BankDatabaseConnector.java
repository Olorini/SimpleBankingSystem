package com.github.olorini.bank.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BankDatabaseConnector {

	private static final Logger LOGGER = Logger.getLogger(BankDatabaseConnector.class.getName());
	private Connection connection;

	public BankDatabaseConnector(String fileName) {
		String url = "jdbc:sqlite:db/" + fileName;
		try {
			this.connection = DriverManager.getConnection(url);
			connection.setAutoCommit(false);
			createCardsTable();
			connection.commit();
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
		}
	}

	public void addCard(String cardNumber, String pin) {
		String query = "INSERT INTO cards(card_number, pin) VALUES (?, ?)";
		if (connection != null) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, cardNumber);
				statement.setString(2, pin);
				 statement.executeUpdate();
				 connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					LOGGER.severe(ex.getMessage());
				}
			}
		}
	}

	public void deleteCard(String cardNumber, String pin) {
		String query = "DELETE FROM cards WHERE card_number = ? AND pin = ?";
		if (connection != null) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, cardNumber);
				statement.setString(2, pin);
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					LOGGER.severe(ex.getMessage());
				}
			}
		}
	}

	public boolean isValidCardData(String cardNumber, String pin) {
		String query = "SELECT * FROM cards WHERE card_number = ? AND pin = ?";
		if (connection != null) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, cardNumber);
				statement.setString(2, pin);
				ResultSet resultSet = statement.executeQuery();
				connection.commit();
				return resultSet.next();
			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		return false;
	}

	public boolean isCardExist(String cardNumber) {
		String query = "SELECT * FROM cards WHERE card_number = ?";
		if (connection != null) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, cardNumber);
				ResultSet resultSet = statement.executeQuery();
				connection.commit();
				return resultSet.next();
			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		return false;
	}

	public int getBalance(String cardNumber) {
		String query = "SELECT balance FROM cards WHERE card_number = ?";
		if (connection != null) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, cardNumber);
				ResultSet resultSet = statement.executeQuery();
				connection.commit();
				return resultSet.getInt("balance");
			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		return 0;
	}

	public void addIncome(String cardNumber, int income) {
		try {
			changeBalance(cardNumber, income, true);
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				LOGGER.severe(ex.getMessage());
			}
		}
	}

	public void transferMoney(String senderCardNumber, String receiverCardNumber, int amount) {
		try {
			changeBalance(senderCardNumber, amount, false);
			changeBalance(receiverCardNumber, amount, true);
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				LOGGER.severe(ex.getMessage());
			}
		}
	}

	private void changeBalance(String cardNumber, int amount, boolean isDebet) throws SQLException {
		String query;
		if (isDebet) {
			query = "UPDATE cards SET balance = balance + ? WHERE card_number = ?";
		} else {
			query = "UPDATE cards SET balance = balance - ? WHERE card_number = ?";
		}
		if (connection != null) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, amount);
				statement.setString(2, cardNumber);
				statement.executeUpdate();
			}
		}
	}

	private int createCardsTable() throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS cards(" +
				"id INTEGER PRIMARY KEY," +
				" card_number VARCHAR(16) NOT NULL," +
				" pin VARCHAR(4) NOT NULL," +
				" balance INTEGER DEFAULT 0)";
		if (connection != null) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				return statement.executeUpdate();
			}
		}
		return 0;
	}
}

