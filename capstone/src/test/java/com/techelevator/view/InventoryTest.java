package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;

public class InventoryTest extends TestCase {

    Inventory sut = new Inventory();


    //checks snackArrayList for matching identifier/code, returns null if no match or if match but inventory=0
    //returns reference of the matching VendingMachineSnack inside list if enough inventory

    public void testIfCodeMatchesReturnSnack() {

        VendingMachineSnack testSnack = sut.ifCodeMatchesReturnSnack("A1"); //code matches
        Assert.assertEquals(testSnack.getName(), "Potato Crisps");

        testSnack=sut.ifCodeMatchesReturnSnack("a1"); //no match
        Assert.assertEquals(null,testSnack);


        testSnack=sut.ifCodeMatchesReturnSnack("A1"); //code matches

        //dispense to change inventory
        sut.dispense(testSnack);
        sut.dispense(testSnack);
        sut.dispense(testSnack);
        sut.dispense(testSnack);

        testSnack=sut.ifCodeMatchesReturnSnack("A1"); //code matches and inventory now = 1
        Assert.assertEquals(testSnack.getInventory(),1);
        sut.dispense(testSnack);
        Assert.assertEquals(testSnack.getInventory(),0); //inventory =0

        testSnack=sut.ifCodeMatchesReturnSnack("A1");

        Assert.assertEquals(null,testSnack); //code matches but inventory is 0 so null

        testSnack=sut.ifCodeMatchesReturnSnack("B1");
        sut.dispense(testSnack); //testing dispense message for all types
        testSnack=sut.ifCodeMatchesReturnSnack("C1");
        sut.dispense(testSnack); //testing dispense message for all types
        testSnack=sut.ifCodeMatchesReturnSnack("D1");
        sut.dispense(testSnack); //testing dispense message for all types

        //also discovered during testing that it is possible to get to inventory of -1
        //if certain methods are called in the wrong order
        //dispense must be called only after verifying inventory using "ifCodeMatchesReturnSnack"
        //may update this in the future







    }

}