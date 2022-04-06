package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Menu;
import com.techelevator.view.MoneyHandler;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS =
			{ MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Menu menu;

	private Inventory inventory=new Inventory();
	//private MoneyHandler moneyHandler= new MoneyHandler(menu);

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run(MoneyHandler moneyHandler) {

		//reads inventory
		inventory.readInventory();

		boolean keepRunning=true;
		while (keepRunning) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				System.out.println("Option 1 Selected");
				inventory.printInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				moneyHandler.run();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){


				keepRunning=false;

			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		MoneyHandler moneyHandler=new MoneyHandler(menu);
		cli.run(moneyHandler);
	}
}
