package com.github.olorini.bank.pages;

import com.github.olorini.bank.actions.CreateAccountAction;
import com.github.olorini.bank.actions.ExitAction;
import com.github.olorini.bank.actions.LogInAction;

public class StartPage extends Page {

	public StartPage() {
		actions.put(1, new CreateAccountAction(this));
		actions.put(2, new LogInAction(this));
		actions.put(0, new ExitAction());
	}

}
