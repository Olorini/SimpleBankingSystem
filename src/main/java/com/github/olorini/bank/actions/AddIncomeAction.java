package com.github.olorini.bank.actions;

import com.github.olorini.bank.Context;
import com.github.olorini.bank.pages.IPage;

import java.util.Scanner;

public class AddIncomeAction implements IAction {

	private final IPage nextPage;

	public AddIncomeAction(IPage nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public void execute() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter income:");
		int income = sc.nextInt();
		Context context = Context.getInstance();
		context.addCurrentCardIncome(income);
		System.out.println("Income was added!");
		nextPage.show();
	}

	@Override
	public String toString() {
		return "Add income";
	}
}
