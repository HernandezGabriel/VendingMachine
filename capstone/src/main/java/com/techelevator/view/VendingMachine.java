package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

import java.util.Scanner;

public class VendingMachine {
    private static Menu menu = new Menu(System.in, System.out);
    private Inventory inventory= new Inventory();
    private MoneyHandler moneyHandler= new MoneyHandler(menu);
    private static VendingMachineCLI cli = new VendingMachineCLI(menu);

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        cli.run(vendingMachine);
    }

    public void runMoneyHandler(VendingMachine vendingMachine) {
        moneyHandler.run(vendingMachine);
    }

    public void printInventory(){
        inventory.printInventory();
    }

    protected void productSelection() {
        printInventory();
        System.out.println("Please enter the product identifier");
        Scanner sc = new Scanner(System.in);
        String identifier = sc.next();

        VendingMachineSnack tempSnack = inventory.ifCodeMatchesReturnSnack(identifier);

        if (tempSnack != null) {
            double price = tempSnack.getPrice();
            if (moneyHandler.areEnoughFunds(price)) {

                moneyHandler.subtractFromBalance(price);
                inventory.dispense(tempSnack);

            }
        }
    }


}



