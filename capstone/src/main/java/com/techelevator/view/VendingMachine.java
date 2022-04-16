package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.techelevator.view.Log.log;


public class VendingMachine {

    //Vending machine a menu, an inventory, a money handler, and a CLI
    public static Menu menu = new Menu(System.in, System.out);
    private Inventory inventory = new Inventory();
    private MoneyHandler moneyHandler = new MoneyHandler();
    private static VendingMachineCLI cli = new VendingMachineCLI(menu);

    public static void main(String[] args) {
        VendingMachine vendingMachine= new VendingMachine();
        cli.run(vendingMachine);

    }

    public void runMoneyHandler(VendingMachine vendingMachine) {

        moneyHandler.run(vendingMachine);

    }

    public void printInventory() {

        inventory.printInventory();

    }

    public void productSelection() {

        printInventory();
        System.out.println("Please enter the product identifier");
        Scanner sc = new Scanner(System.in);
        String identifier = sc.next();

        VendingMachineSnack tempSnack = inventory.ifCodeMatchesReturnSnack(identifier);

        if (tempSnack != null) {
            double price = tempSnack.getPrice();
            if (moneyHandler.areEnoughFunds(price)) {

                double previousBalance = moneyHandler.getBalance();
                moneyHandler.subtractFromBalance(price);

                inventory.dispense(tempSnack);

                writeLog(tempSnack.getName(), previousBalance, moneyHandler.getBalance());

            }
        }
    }

    public static void writeLog(String transaction, double was, double currentBalance) {

        DecimalFormat df = new DecimalFormat("0.00");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDate = dateTime.format(myFormatObj);

        log(formattedDate + " " + transaction + " " + df.format(was) + " " + df.format(currentBalance));

    }

    public void generateSalesLog(){

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy hh-mm-ss a ");
        String formattedDate = dateTime.format(myFormatObj);
        File filepath = new File(formattedDate + "Sales Log");

        try(PrintWriter salesLogWriter= new PrintWriter(filepath)){
           salesLogWriter.print(inventory.printInventoryForSalesReport());
        }
        catch (FileNotFoundException ex){
            System.err.print(ex.getMessage());
        }

    }



}




