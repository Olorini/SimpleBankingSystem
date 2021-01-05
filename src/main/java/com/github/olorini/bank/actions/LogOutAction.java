package com.github.olorini.bank.actions;

import com.github.olorini.bank.pages.StartPage;

public class LogOutAction implements IAction {

	public LogOutAction() { }

	@Override
	public void execute() {
		System.out.println("You have successfully logged out!");
		new StartPage().show();
	}

	@Override
	public String toString() {
		return "Log out";
	}
}
