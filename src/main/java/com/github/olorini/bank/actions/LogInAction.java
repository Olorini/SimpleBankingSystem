package com.github.olorini.bank.actions;

import com.github.olorini.bank.Context;
import com.github.olorini.bank.pages.IPage;
import com.github.olorini.bank.pages.UserPage;
import com.github.olorini.bank.products.Card;

import java.util.Scanner;

public class LogInAction implements IAction {

	private final IPage previousPage;

	public LogInAction(IPage previousPage) {
		this.previousPage = previousPage;
	}

	@Override
	public void execute() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your card number:");
		String cardNumber = sc.nextLine();
		System.out.println("Enter your PIN:");
		String pin = sc.nextLine();
		Context context = Context.getInstance();
		Card card = new Card(cardNumber, pin);
		if (context.isValidCardData(card)) {
			context.setCurrentCard(card);
			System.out.println("You have successfully logged in!");
			new UserPage().show();
		} else {
			System.out.println("Wrong card number or PIN!");
			previousPage.show();
		}
	}

	@Override
	public String toString() {
		return "Log into account";
	}
}
