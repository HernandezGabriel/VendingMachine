package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN= "";
	private static final String[] MAIN_MENU_OPTIONS =
			{ MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT,MAIN_MENU_OPTION_HIDDEN} ;

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run(VendingMachine vendingMachine) {

		boolean keepRunning=true;
		while (keepRunning) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				vendingMachine.printInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//jumps to MoneyHandler class
				vendingMachine.runMoneyHandler(vendingMachine);

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){

				keepRunning=false;

			} else if (choice.equals(MAIN_MENU_OPTION_HIDDEN)){

				System.out.println("Sales Report Selected");
				vendingMachine.generateSalesLog();

			}
		}
	}


}
