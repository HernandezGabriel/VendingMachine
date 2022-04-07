package com.techelevator.view;

import java.util.Scanner;

public class MoneyHandler {

    private static double balance=0.0;


    private Menu menu;
//    public static double QPENNY = .01;
//    public static final double QUARTER = .25;
//    public static final double DIME = .1;
//    public static final double NICKEL = .05;
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

    private static void setBalanceZero(){
        balance=0;
    }

    public void run(){
        boolean keepRunning=true;
        while (keepRunning) {
            String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                feedMoney();

            } else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

                VendingMachine.productSelection();



            } else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){

                System.out.println("transaction finished, Returning change");
                returnChange();
                keepRunning=false;
            }


            System.out.print("Current money provided: $" );
            System.out.printf("%.02f", balance);
        }

    }
    public static boolean areEnoughFunds(double amntToSubtract){
        if((balance-amntToSubtract)>0){
            return true;
        }
        else{
            System.out.println("not enough funds");
            return false;}
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

    public void returnChange(){
        double balanceD;

        double balanceWas = balance;
        balanceD = (balance*100);
        int quarters = (int) (balanceD/25);
        balanceD = balanceD - (quarters*25);
        int dimes = (int) (balanceD/10);
        balanceD = balanceD - (dimes*10);
        int nickels = (int) (balanceD/5);

        setBalanceZero();

        //Money.balance = balance.multiply(new BigDecimal(0.00));
        //writeLog("GIVE CHANGE", balanceWas, balance);
        //balanceWas = balanceWas.multiply(new BigDecimal(0.00));
        System.out.println("CHANGE: "+ quarters + " quarters " + dimes + " dimes " + nickels + " nickels.");
    }


//        double currentBalance= getBalance();
//        while(currentBalance!=0){
//            if (currentBalance)
//        }

    }


