package com.github.olorini.bank.actions;

import com.github.olorini.bank.Context;
import com.github.olorini.bank.pages.IPage;
import com.github.olorini.bank.products.Card;
import com.github.olorini.bank.utils.CardUtils;

public class CreateAccountAction implements IAction {

	private final IPage nextPage;

	public CreateAccountAction(IPage nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public void execute() {
		String truncatedCardNumber = "400000" + CardUtils.generateNumberLine(9);
		int checkSum = CardUtils.getCheckSum(truncatedCardNumber);
		String cardNumber = truncatedCardNumber + checkSum;
		String pin = CardUtils.generateNumberLine(4);
		Card card = new Card(cardNumber, pin);
		Context.getInstance().addCard(card);
		System.out.println("Your card number:");
		System.out.println(card.getNumber());
		System.out.println("Your card PIN:");
		System.out.println(card.getPin());
		nextPage.show();
	}

	@Override
	public String toString() {
		return "Create an account";
	}
}
