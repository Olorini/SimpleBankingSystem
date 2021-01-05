package com.github.olorini.bank.actions;

import com.github.olorini.bank.Context;
import com.github.olorini.bank.pages.StartPage;

public class CloseAccountAction implements IAction {

	public CloseAccountAction() { }

	@Override
	public void execute() {
		Context context = Context.getInstance();
		context.deleteCard();
		System.out.println("The account has been closed!");
		new StartPage().show();
	}

	@Override
	public String toString() {
		return "Close account";
	}
}
