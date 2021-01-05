package com.github.olorini.bank;

import com.github.olorini.bank.connector.BankDatabaseConnector;
import com.github.olorini.bank.pages.IPage;
import com.github.olorini.bank.pages.StartPage;

public class Main {
	public static void main(String[] args) {
		if (args.length > 1 && "-fileName".equals(args[0])) {
			String dbName = args[1];
			if (null != dbName && !"".equals(dbName)) {
				Context.getInstance().setConnector(new BankDatabaseConnector(dbName));
				IPage startPage = new StartPage();
				startPage.show();
			} else {
				System.out.println("Database file name is empty");
			}
		} else {
			System.out.println("Command line argument '-fileName' isn't found");
		}
	}
}
