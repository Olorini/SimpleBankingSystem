package com.github.olorini.bank.actions;

import com.github.olorini.bank.Context;
import com.github.olorini.bank.pages.IPage;
import com.github.olorini.bank.utils.CardUtils;

import java.util.Scanner;

public class DoTransferAction implements IAction {

	private final IPage nextPage;

	public DoTransferAction(IPage nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public void execute() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Transfer");
		System.out.println("Enter card number:");
		String cardNumber = sc.nextLine();
		if (CardUtils.isValidCardNumber(cardNumber)) {
			Context context = Context.getInstance();
			if (context.isCardExist(cardNumber)) {
				System.out.println("Enter how much money you want to transfer:");
				int amount = sc.nextInt();
				if (context.getCurrentCardBalance() >= amount) {
					context.transferMoney(cardNumber, amount);
					System.out.println("Success!");
				} else {
					System.out.println("Not enough money!");
				}
			} else {
				System.out.println("Such a card does not exist.");
			}
		} else {
			System.out.println("Probably you made mistake in the card number. Please try again!");
		}
		nextPage.show();
	}

	@Override
	public String toString() {
		return "Do transfer";
	}
}
