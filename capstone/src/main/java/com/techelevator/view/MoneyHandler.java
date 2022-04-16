package com.techelevator.view;

import java.util.Scanner;

import static com.techelevator.view.VendingMachine.writeLog;

public class MoneyHandler {

    private double balance=0.0;
    private static final Menu menu = VendingMachine.menu;

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS =
            { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT,PURCHASE_MENU_OPTION_FINISH_TRANSACTION };


    public double getBalance() {
        return balance;
    }

    private void setBalanceZero(){
        balance=0;
    }

    //runs menu options for the purchase menu and jumps to various methods
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

    public boolean areEnoughFunds(double amntToSubtract){

        if((balance-amntToSubtract)>0){
            return true;
        }
        else{
            System.out.println("not enough funds");
            return false;}

    }


    public void subtractFromBalance(double amntToSubtract) {

        balance = balance-amntToSubtract;
    }

    public void feedMoney () {

        System.out.println("Please enter one bill at a time: $1 $2 $5 $10");
        Scanner sc = new Scanner(System.in);
        String feed = sc.next();

        double previousBalance=getBalance();


        if (feed.equals("$1")) {
            this.balance = balance+1;
        }
        else if (feed.equals("$2")) {
            balance = balance+2;
        }
        else if (feed.equals("$5")) {
            balance = balance+5;
        }
        else if (feed.equals("$10")) {
            balance = balance+10;
        }
        else{
            System.out.println("Invalid Input");
        }

        writeLog("FEED MONEY",previousBalance,getBalance());

    }

    public void returnChange(){

        double balanceD; //changing balance variable

        double balanceWas = balance;
        balanceD = (balance*100);
        int quarters = (int) (balanceD/25);
        balanceD = balanceD - (quarters*25);
        int dimes = (int) (balanceD/10);
        balanceD = balanceD - (dimes*10);
        int nickels = (int) (balanceD/5);

        setBalanceZero();

        System.out.println("CHANGE: "+ quarters + " quarters " + dimes + " dimes " + nickels + " nickels.");

        writeLog("GIVE CHANGE",balanceWas,getBalance());

    }

}


