package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

import java.util.Scanner;

public class VendingMachine {
    static Menu menu = new Menu(System.in, System.out);
    private static Inventory inventory= new Inventory();
    private static MoneyHandler moneyHandler= new MoneyHandler(menu);
    static VendingMachineCLI cli = new VendingMachineCLI(menu);

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        inventory.readInventory();
        cli.run(vendingMachine);
    }

    public void runMoneyHandler() {
        moneyHandler.run();
    }

    public static void printInventory(){
        inventory.printInventory();
    }

    public static void productSelection() {
        VendingMachine.printInventory();
        System.out.println("Please enter the product identifier");
        Scanner sc = new Scanner(System.in);
        String identifier = sc.next();

        VendingMachineSnack tempSnack = Inventory.ifMatchesReturnSnack(identifier);

        if (tempSnack != null) {
            double price = tempSnack.getPrice();
            if (MoneyHandler.areEnoughFunds(price)) {
                MoneyHandler.subtractFromBalance(price);
                inventory.dispense(tempSnack);

            }
        }
    }


}



