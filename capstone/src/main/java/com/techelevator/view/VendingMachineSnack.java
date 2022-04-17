package com.techelevator.view;

public class VendingMachineSnack {

    private String name;
    private double price;
    private String identifier;
    private String snackType;

    private int beginningInventory=5;
    private int amountSold =0;

    public int getAmountSold() {
        return amountSold;
    }

    public String getName() {
        return name;
    }

    public VendingMachineSnack(String identifier, String name, double price, String snackType) {

        this.name = name;
        this.price = price;
        this.identifier = identifier;
        this.snackType=snackType;

    }

    @Override
    public String toString() {

        return identifier+" "+ name +" " + price +" "+snackType+" "+ beginningInventory;

    }


    public String toStringForSalesReport(){

        return name+ " | " + amountSold + "\n";

    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getInventory(){
        return this.beginningInventory;
    }

    public void subtractInventory(){
        this.beginningInventory--;
    }

    public void addToAmountSold(){
        amountSold++;
    }

    public double getPrice() {
        return this.price;
    }

    public String dispenseMessage(){

        if (snackType.equals("Candy")){
            return "Munch Munch, Yum!";

        }
        if(snackType.equals("Chip")){
            return "Crunch Crunch, Yum!";

        }
        if(snackType.equals("Drink")){
            return "Glug Glug, Yum!";
        }
        if(snackType.equals("Gum")) {
            return "Chew Chew, Yum!";
        }

        return "";

    }


}


