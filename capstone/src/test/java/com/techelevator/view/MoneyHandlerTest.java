package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.techelevator.view.VendingMachine.menu;

public class MoneyHandlerTest extends TestCase {

    MoneyHandler sut;
    InputStream sysInBackup = System.in;   // backup System.in to restore it later
    PrintStream sysOutBackup = System.out;

    public void testFeedMoney() {
         sut = new MoneyHandler();
        assertEquals(0.0,sut.getBalance());



        ByteArrayInputStream in = new ByteArrayInputStream("$1".getBytes()); //tests $1
        System.setIn(in);
        sut.feedMoney();
        assertEquals(1.00, sut.getBalance());

        in= new ByteArrayInputStream("$2".getBytes()); //tests $2
        System.setIn(in);
        sut.feedMoney();
        assertEquals(3.00, sut.getBalance());

        in = new ByteArrayInputStream("5".getBytes()); //tests 5, fails because not valid input
        System.setIn(in);
        sut.feedMoney();
        assertEquals(3.00, sut.getBalance());

        System.setIn(sysInBackup); //System.in backup


    }


    public void testAreEnoughFunds() {
        sut = new MoneyHandler();
        assertEquals(0.0,sut.getBalance()); // check balance =0

        ByteArrayInputStream in = new ByteArrayInputStream("$1".getBytes()); //tests $1
        System.setIn(in);
        sut.feedMoney();//feed 1$

        assertEquals(1.0,sut.getBalance()); //check balance =1


        assertTrue(sut.areEnoughFunds(1)); //check if enough money with balance left being 0

        sut.subtractFromBalance(1); //test subtractFromBalance()
        assertEquals(0.0,sut.getBalance());

        assertFalse(sut.areEnoughFunds(1)); //checks for false since not enough money

        //checks if balance can go negative, it can. may update in the future
        sut.subtractFromBalance(50);
        assertEquals(-50.0,sut.getBalance());


        System.setIn(sysInBackup); //System.in backup

    }

    public void testReturnChange() {
        sut = new MoneyHandler();
        assertEquals(0.0,sut.getBalance());


        ByteArrayInputStream in = new ByteArrayInputStream("$2".getBytes()); //feed $2
        System.setIn(in);
        sut.feedMoney();


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();//saves console output from returnChange()
        System.setOut(new PrintStream(outContent));

        sut.returnChange();

        String comparisonString=outContent.toString();
        assertEquals("CHANGE: 8 quarters 0 dimes 0 nickels.\r\n", comparisonString); //tests for 8q

        in = new ByteArrayInputStream("$2".getBytes()); //feed $2
        System.setIn(in);
        sut.feedMoney();
        assertEquals(2.00, sut.getBalance()); //checks for $2
        sut.subtractFromBalance(1.75);
        assertEquals(0.25,sut.getBalance());

        outContent = new ByteArrayOutputStream();//saves console output from returnChange()
        System.setOut(new PrintStream(outContent));

        sut.returnChange();

        comparisonString=outContent.toString();
        assertEquals("CHANGE: 1 quarters 0 dimes 0 nickels.\r\n", comparisonString); //tests for 1q

        in = new ByteArrayInputStream("$2".getBytes()); //feed $2
        System.setIn(in);
        sut.feedMoney();
        assertEquals(2.00, sut.getBalance()); //checks for $2
        sut.subtractFromBalance(1.60);
        assertEquals(0.40,sut.getBalance());

        outContent = new ByteArrayOutputStream();//saves console output from returnChange()
        System.setOut(new PrintStream(outContent));

        sut.returnChange();

        comparisonString=outContent.toString();
        assertEquals("CHANGE: 1 quarters 1 dimes 1 nickels.\r\n", comparisonString); //tests for 1q,1d,1n



        outContent = new ByteArrayOutputStream();//saves console output from returnChange()
        System.setOut(new PrintStream(outContent));

        sut.returnChange();
        comparisonString=outContent.toString();
        assertEquals("CHANGE: 0 quarters 0 dimes 0 nickels.\r\n", comparisonString); //test for balance of 0


        //test returnChange with negative 5 in balance
        sut.subtractFromBalance(5);

        outContent = new ByteArrayOutputStream();//saves console output from returnChange()
        System.setOut(new PrintStream(outContent));

        sut.returnChange();
        comparisonString=outContent.toString();
        assertEquals("CHANGE: -20 quarters 0 dimes 0 nickels.\r\n", comparisonString); //tests negative

        // returns -20 quarters, may update in the future to better handle negative values



        //test with 4 pennies remaining.
        in = new ByteArrayInputStream("$2".getBytes()); //feed $2
        System.setIn(in);
        sut.feedMoney();
        assertEquals(2.00, sut.getBalance()); //checks for $2
        sut.subtractFromBalance(1.96);
        assertEquals(0.04,sut.getBalance());

        outContent = new ByteArrayOutputStream();//saves console output from returnChange()
        System.setOut(new PrintStream(outContent));

        sut.returnChange();
        comparisonString=outContent.toString();
        assertEquals("CHANGE: 0 quarters 0 dimes 0 nickels.\r\n", comparisonString);
        //returns string above because we don't have pennies
        assertEquals(0.0,sut.getBalance()); //checks balance is 0 after returnChange()


        System.setOut(sysOutBackup);;
        System.setIn(sysInBackup); //System.in backup

    }
}