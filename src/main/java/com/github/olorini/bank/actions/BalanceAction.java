package com.github.olorini.bank.actions;

import com.github.olorini.bank.Context;
import com.github.olorini.bank.pages.IPage;

public class BalanceAction implements IAction {

	private final IPage nextPage;

	public BalanceAction(IPage nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public void execute() {
		Context context = Context.getInstance();
		int balance = context.getCurrentCardBalance();
		System.out.println("Balance: " + balance);
		nextPage.show();
	}

	@Override
	public String toString() {
		return "Balance";
	}
}
