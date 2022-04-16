package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;

public class InventoryTest extends TestCase {

    Inventory testInventory = new Inventory();

    public void testIfCodeMatchesReturnSnack() {

        VendingMachineSnack testSnack = testInventory.ifCodeMatchesReturnSnack("A1");
        Assert.assertEquals(testSnack.getName(), "Potato Crisps");

        testSnack=testInventory.ifCodeMatchesReturnSnack("a1");
        Assert.assertEquals(null,testSnack);


        testSnack=testInventory.ifCodeMatchesReturnSnack("A1");
        testInventory.dispense(testSnack);
        testInventory.dispense(testSnack);
        testInventory.dispense(testSnack);
        testInventory.dispense(testSnack);
        testInventory.dispense(testSnack);

        Assert.assertEquals(0, testSnack.getInventory());

    }
}