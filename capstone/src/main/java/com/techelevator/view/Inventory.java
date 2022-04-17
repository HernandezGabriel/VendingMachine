package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class Inventory {

    private ArrayList<VendingMachineSnack> snackArrayList= new ArrayList();

    //Calls readInventory() on initialization to populate snackArrayList
    public Inventory(){
        readInventory();
    }

    //called from productSelection inside VendingMachine Class
    //checks snackArrayList for matching identifier/code, returns null if no match or if match but inventory=0
    //returns reference of the matching VendingMachineSnack inside list if enough inventory
    public VendingMachineSnack ifCodeMatchesReturnSnack(String code) {

        for (int i = 0; i < snackArrayList.size(); i++) {
            if(snackArrayList.get(i).getIdentifier().equals(code)){
                //check inventory
                if(snackArrayList.get(i).getInventory()>0){
                    return snackArrayList.get(i);
                }
                else{ //matches but no inventory
                    System.out.println("Product sold out (inventory=0)");
                    return null;
                }
            }
        }
        System.out.println("Product Not Found");
        return null;

    }

    //prints dispense message, subtracts inventory, adds to amountSold, prints corresponding text to console
    //called from productSelection() inside vendingMachine
    //called after ifCodeMatchesReturnSnack() and after some MoneyHandler methods that verify funding
    public void dispense(VendingMachineSnack temp){

        System.out.println(temp.dispenseMessage());
        temp.subtractInventory();
        temp.addToAmountSold();
        System.out.println(temp.getName()+" "+ temp.getPrice()+ " Remaining Inventory: "+temp.getInventory());

    }

    //uses a hard coded filepath "vendingmachine.csv" to open a file
    //converts file to a string and then to a string array split using "|"
    //uses VendingMachineSnack constructor and stringArray to initialize a new snack and then adds it to the snackArrayList
    public void readInventory(){

        String filepath=
                "C:\\Users\\herna\\Desktop\\Capstone\\module-1-capstone-gabriel-hernandez-daniel-tilton\\capstone\\vendingmachine.csv";
        File dataFile = new File(filepath);


        try(Scanner fin = new Scanner(dataFile);){
            while(fin.hasNextLine()){
                String lineOfInput=fin.nextLine();
                String[] lineOfInputToArray=lineOfInput.split("\\|");

                VendingMachineSnack tempSnack=
                        new VendingMachineSnack(lineOfInputToArray[0],lineOfInputToArray[1],
                                Double.parseDouble(lineOfInputToArray[2]),lineOfInputToArray[3]);

                snackArrayList.add(tempSnack);

            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not opened");

        }


    }

    //Prints each snack in snackArrayList using toString()
    public void printInventory(){

        for (VendingMachineSnack s: snackArrayList) {
            System.out.println(s.toString());
        }

    }

    //gets amountSold and price of each snack to calculate total sales
    //utilizes toStringForSalesReport inside Snack Class to return a string with name of snack and amount sold
    //returns SalesReport as a string
    public String printSalesReport(){

        String returnString="";
        double totalSales=0;
        for (VendingMachineSnack s: snackArrayList) {

            totalSales+=(s.getAmountSold())*s.getPrice(); //add to totalSales

            returnString+=s.toStringForSalesReport(); //add to returnString

        }
        DecimalFormat df = new DecimalFormat("0.00");

        returnString+="\n"+ "TOTAL SALES: "+ df.format(totalSales) +"$";
        return returnString;

    }






}
