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

    //Vending machine class contains an inventory handler, a money handler, and the VendingMachineCLI
    //readsInventory on inventory initialization
    public static Menu menu = new Menu(System.in, System.out);
    private Inventory inventory = new Inventory();
    private MoneyHandler moneyHandler = new MoneyHandler();

    //static because main is static
    private static VendingMachineCLI cli= new VendingMachineCLI(menu);

    public static void main(String[] args) {

        //VendingMachine obj is passed to the cli.run() method to allow for method access
        //program starts with initializing vendingMachine and running VendingMachineCLI
        VendingMachine vendingMachine= new VendingMachine();
        cli.run(vendingMachine);

    }

    //method to jump to moneyHandler.run, passing the vendingMachine obj for access to productSelection()
    public void runMoneyHandler(VendingMachine vendingMachine) {

        moneyHandler.run(vendingMachine);

    }

    //method jumps to inventory obj and calls printInventory()
    //inventory populates on initialization
    public void printInventory() {

        inventory.printInventory();

    }

    //method connects functionality from inventory and money handler to dispense a snack for the user
    //initially called from the moneyHandler inside the purchase menu
    //first prints inventory, takes a user input and calls
    //on inventory.ifCodeMatchesReturnSnack(userInput) to return null or return a snack
    //calls areEnoughFunds() and subtractFromBalance() from moneyHandler
    //calls dispense() from inventory and calls writeLog()
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

    // Utilizes the imported static method log() to write different transactions to a file
    // Called from returnChange() and feedMoney() methods inside the MoneyHandler class to log such transactions to a file
    public static void writeLog(String transaction, double was, double currentBalance) {

        DecimalFormat df = new DecimalFormat("0.00");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDate = dateTime.format(myFormatObj);

        log(formattedDate + " " + transaction + " " + df.format(was) + " " + df.format(currentBalance));

    }


    //utilizes printSalesReport() inside inventory and writes it to a new file: "DATE+ Sales Log"
    public void generateSalesLog(){

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy hh-mm-ss a ");
        String formattedDate = dateTime.format(myFormatObj);
        File filepath = new File(formattedDate + "Sales Log");

        try(PrintWriter salesLogWriter= new PrintWriter(filepath)){
           salesLogWriter.print(inventory.printSalesReport());
        }
        catch (FileNotFoundException ex){
            System.err.print(ex.getMessage());
        }

    }



}




