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


	// Uses the previously set up menu and 3 methods from the vending machine class
	// to carry out user selected functions
	public void run(VendingMachine vendingMachine) {

		boolean keepRunning=true;
		while (keepRunning) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				//jumps to vending machine then to inventory class to
				// print out the inventory of snacks
				// Inventory was populated on initialization of Inventory class
				vendingMachine.printInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				//jumps to vending machine then to Money handler to start the menu
				vendingMachine.runMoneyHandler(vendingMachine);

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				//user exits
				keepRunning=false;

			} else if (choice.equals(MAIN_MENU_OPTION_HIDDEN)){

				//hidden menu option to generate sales log
				// calls method "generateSalesLog()" in Vending Machine class
				// utilizes our changes to the provided Menu class to allow for hidden options ie to stop the ")"
				System.out.println("Sales Report Has Been Generated");
				vendingMachine.generateSalesLog();

			}
		}
	}


}
