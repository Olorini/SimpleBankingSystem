package com.github.olorini.bank.pages;

import com.github.olorini.bank.actions.IAction;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Page implements IPage {

	protected final Map<Integer, IAction> actions = new LinkedHashMap<>();

	public IAction getAction(int key) {
		return actions.get(key);
	}

	@Override
	public void show() {
		Scanner sc = new Scanner(System.in);
		for (Map.Entry<Integer, IAction> entry : actions.entrySet()) {
			System.out.println(entry.getKey() + ". " + entry.getValue());
		}
		if (sc.hasNextInt()) {
			int actionNumber = sc.nextInt();
			handle(actionNumber);
		}
	}

	private void handle(int actionNumber) {
		try {
			IAction action = getAction(actionNumber);
			action.execute();
		} catch (IndexOutOfBoundsException e) {
			//
		}
	}
}
