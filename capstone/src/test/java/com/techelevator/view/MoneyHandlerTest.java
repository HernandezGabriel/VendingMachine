package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.techelevator.view.VendingMachine.menu;

public class MoneyHandlerTest extends TestCase {

    MoneyHandler sut = new MoneyHandler();

    public void testGetBalance() {
        assertEquals(0.0,sut.getBalance());

        InputStream sysInBackup = System.in; // backup System.in to restore it later

        ByteArrayInputStream in = new ByteArrayInputStream("$1".getBytes());
        System.setIn(in);
        sut.feedMoney();
        assertEquals(1.00, sut.getBalance());

        in= new ByteArrayInputStream("$2".getBytes());
        System.setIn(in);
        sut.feedMoney();
        assertEquals(3.00, sut.getBalance());

        in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        sut.feedMoney();
        assertEquals(3.00, sut.getBalance());



        System.setIn(sysInBackup);


    }


    public void testAreEnoughFunds() {
    }

    public void testSubtractFromBalance() {
    }

    public void testFeedMoney() {
    }

    public void testReturnChange() {
    }
}