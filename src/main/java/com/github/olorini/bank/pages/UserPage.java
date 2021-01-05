package com.github.olorini.bank.pages;

import com.github.olorini.bank.actions.AddIncomeAction;
import com.github.olorini.bank.actions.BalanceAction;
import com.github.olorini.bank.actions.CloseAccountAction;
import com.github.olorini.bank.actions.DoTransferAction;
import com.github.olorini.bank.actions.ExitAction;
import com.github.olorini.bank.actions.LogOutAction;

public class UserPage extends Page {

	public UserPage() {
		actions.put(1, new BalanceAction(this));
		actions.put(2, new AddIncomeAction(this));
		actions.put(3, new DoTransferAction(this));
		actions.put(4, new CloseAccountAction());
		actions.put(5, new LogOutAction());
		actions.put(0, new ExitAction());
	}
}
