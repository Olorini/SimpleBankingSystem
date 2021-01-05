package com.github.olorini.bank;

import com.github.olorini.bank.connector.BankDatabaseConnector;
import com.github.olorini.bank.products.Card;

public class Context {

	private static final Context instance = new Context();

	private Card currentCard;
	private BankDatabaseConnector connector;

	private Context() { }

	public static Context getInstance() {
		return instance;
	}

	public void addCard(Card card) {
		connector.addCard(card.getNumber(), card.getPin());
	}

	public void deleteCard() {
		connector.deleteCard(currentCard.getNumber(), currentCard.getPin());
	}

	public boolean isValidCardData(Card card) {
		return connector.isValidCardData(card.getNumber(), card.getPin());
	}

	public boolean isCardExist(String cardNumber) {
		return connector.isCardExist(cardNumber);
	}

	public void transferMoney(String cardNumber, int amount) {
		connector.transferMoney(currentCard.getNumber(), cardNumber, amount);
	}

	public Integer getCurrentCardBalance() {
		return connector.getBalance(currentCard.getNumber());
	}

	public void closeConnection(){
		connector.closeConnection();
	}

	public void addCurrentCardIncome(int income) {
		connector.addIncome(currentCard.getNumber(), income);
	}

	public void setCurrentCard(Card currentCard) {
		this.currentCard = currentCard;
	}

	public void setConnector(BankDatabaseConnector connector) {
		this.connector = connector;
	}
}
