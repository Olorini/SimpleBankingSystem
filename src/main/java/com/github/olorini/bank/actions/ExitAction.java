package com.github.olorini.bank.actions;

import com.github.olorini.bank.Context;

public class ExitAction implements IAction {

	@Override
	public void execute() {
		Context context = Context.getInstance();
		context.closeConnection();
		System.out.println("Bye!");
	}

	@Override
	public String toString() {
		return "Exit";
	}
}
