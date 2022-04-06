package com.techelevator.view;

import java.util.Scanner;

public class MoneyHandler {

    private static double balance=0.0;


    private Menu menu;
    public static final double PENNY = .01;
    public static final double QUARTER = .25;
    public static final double DIME = .1;
    public static final double NICKEL = .05;
    public static final double ONE = 1;
    public static final double TWO = 2;
    public static final double FIVE = 5;
    public static final double TEN = 10;

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS =
            { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT,PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

    public MoneyHandler(Menu menu){this.menu=menu;}

    public static double getBalance() {
        return balance;
    }

    public void run(){
        boolean keepRunning=true;
        while (keepRunning) {
            String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                feedMoney();

            } else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {





            } else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){

                System.out.println("transaction finished");
                keepRunning=false;
            }


            System.out.print("Current money provided: $" );
            System.out.printf("%.02f", balance);
        }

    }

    public void productSelection(){
        Inventory.printInventory();
        System.out.println("Please enter the product identifier");
        Scanner sc = new Scanner(System.in);
        String feed = sc.next();

        if(Inventory.ifMatches(feed,balance)){
           // subtractFromBalance();


        }else{

        }




    }

    public static void subtractFromBalance(double amntToSubtract) {
        balance = balance-amntToSubtract;
    }

    public void feedMoney () {

        System.out.println("Please enter one bill at a time");
        Scanner sc = new Scanner(System.in);
        String feed = sc.next();

        // boolean keepGoing=true;


        if (feed.equals("$1")) {
            this.balance = balance+ONE;
        }
        else if (feed.equals("$2")) {
            balance = balance+TWO;
        }
        else if (feed.equals("$5")) {
            balance = balance+FIVE;
        }
        else if (feed.equals("$10")) {
            balance = balance+TEN;
        }
//        writeLog("FEED MONEY", pastBalance, balance);
        //System.out.println("BALANCE: " + balance);
    }

}
