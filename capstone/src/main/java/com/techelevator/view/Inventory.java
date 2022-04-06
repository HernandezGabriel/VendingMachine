package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.techelevator.view.MoneyHandler.subtractFromBalance;

public class Inventory {
    static ArrayList<VendingMachineSnack> snackArrayList= new ArrayList();

    public Inventory(){

    }

    public static void ifMatches(String feed, double balance) {
        for (int i = 0; i < snackArrayList.size(); i++) {
            if(snackArrayList.get(i).getIdentifier().equals(feed)){
                //check inventory
                if(snackArrayList.get(i).getInventory()>0){
                    if(snackArrayList.get(i).getPrice()>=balance){


                    }
                }

            }

        }
    }

    public void dispense(VendingMachineSnack temp){
        temp.dispenseMessage();
        temp.subtractInventory();
        subtractFromBalance(temp.getPrice());
        System.out.println(temp.getName()+" "+ temp.getPrice()+" "+ MoneyHandler.getBalance() );

    }

    public void readInventory(){
        String filepath=
                "C:\\Users\\herna\\Desktop\\Capstone\\module-1-capstone-gabriel-hernandez-daniel-tilton\\capstone\\vendingmachine.csv";
        File dataFile = new File(filepath);


        try(Scanner fin = new Scanner(dataFile);){
            while(fin.hasNextLine()){
                String lineOfInput=fin.nextLine();
                String[] lineOfInputToArray=lineOfInput.split("\\|");

                VendingMachineSnack tempSnack=
                        new VendingMachineSnack(lineOfInputToArray[0],lineOfInputToArray[1],Double.parseDouble(lineOfInputToArray[2]),lineOfInputToArray[3]);

                snackArrayList.add(tempSnack);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not opened");

        }


    }

    public static void printInventory(){
        for (VendingMachineSnack s: snackArrayList) {

            System.out.println(s.toString());

        }
    }




}
