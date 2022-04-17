package com.techelevator.view;

import java.math.BigDecimal;
import java.util.Scanner;

import static com.techelevator.view.VendingMachine.writeLog;

public class MoneyHandler {

    private BigDecimal balance= BigDecimal.ZERO;
    private static final Menu menu = VendingMachine.menu;

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS =
            { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT,PURCHASE_MENU_OPTION_FINISH_TRANSACTION };


    public double getBalance() {

        return balance.doubleValue();
    }

    private void setBalanceZero(){
        balance= BigDecimal.ZERO;
    }

    //runs the menu options for the purchase menu inside moneyHandler
    //jumps to one method inside vendingMachine object, productSelection()
    //jumps to two methods within Money Handler class, feedMoney(), returnChange()
    public void run(VendingMachine vendingMachine){

        boolean keepRunning=true;
        while (keepRunning) {
            String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

                feedMoney();

            } else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

                vendingMachine.productSelection();

            } else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){

                System.out.println("transaction finished, Returning change");
                returnChange();
                keepRunning=false;

            }

            System.out.print("Current money provided: $" );
            System.out.printf("%.02f", balance);

        }

    }

    //checks for enough funds to complete a transaction, returns true or false, prints to console.
    public boolean areEnoughFunds(double amountToSubtract){
        BigDecimal testBal=balance.subtract(BigDecimal.valueOf(amountToSubtract));

        if(testBal.compareTo(BigDecimal.ZERO) >= 0){ //returns 0 if equal, 1 if greater, -1 if less than
            return true;
        }
        else{
            System.out.println("not enough funds");
            return false;}

    }

    //subtracts from balance
    public void subtractFromBalance(double amountToSubtract) {

        balance = balance.subtract(BigDecimal.valueOf(amountToSubtract));
    }

    // uses console to accept user input and add money to the Money handler
    // calls on static writeLog() to log such transactions
    public void feedMoney () {

        System.out.println("Please enter one bill at a time: $1 $2 $5 $10");
        Scanner sc = new Scanner(System.in);
        String feed = sc.next();

        double previousBalance=getBalance();


        if (feed.equals("$1")) {
            this.balance = balance.add(BigDecimal.valueOf(1));
        }
        else if (feed.equals("$2")) {
            balance = balance.add(BigDecimal.valueOf(2));
        }
        else if (feed.equals("$5")) {
            balance = balance.add(BigDecimal.valueOf(5));
        }
        else if (feed.equals("$10")) {
            balance = balance.add(BigDecimal.valueOf(10));
        }
        else{
            System.out.println("Invalid Input");
        }

        writeLog("FEED MONEY",previousBalance,getBalance());

    }

    //returns the remaining balance inside MoneyHandler in the smallest number of coins possible
    //calls on static writeLog() to log such transactions
    public void returnChange(){

        double changingBalance; //changing balance variable
        double balanceWas = balance.doubleValue(); //saves balance as a double instead of BigDecimal

        changingBalance = (balanceWas*100);
        int quarters = (int) (changingBalance/25);
        changingBalance = changingBalance - (quarters*25);
        int dimes = (int) (changingBalance/10);
        changingBalance = changingBalance - (dimes*10);
        int nickels = (int) (changingBalance/5);

        setBalanceZero();

        System.out.println("CHANGE: "+ quarters + " quarters " + dimes + " dimes " + nickels + " nickels.");

        writeLog("GIVE CHANGE",balanceWas,getBalance());

    }

}


