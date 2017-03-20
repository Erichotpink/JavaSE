package com.epam.javase.t01.usingsynchronized;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/20/2017.
 */
public class TransferTest {

    int srcBalance = 200;
    int dstBalance = 500;
    int amount = 50;
    Account src = null;
    Account dest = null;

    @Before
    public void createAccountsBeforeTest() {
        src = new Account(srcBalance);
        dest = new Account(dstBalance);
    }

    @Test
    public void shouldSuccessfullyTransferMoneyBetweenDifferentAccounts() throws Exception{
        Thread t = new Thread(new OperatorTransfer(src, dest, srcBalance));
        t.start();
        t.join();

        assertTrue(dest.getBalance() == (dstBalance + srcBalance));
    }

    @Test
    public void shouldSuccessfullyTransferMoneyBetweenEqualAccounts() throws Exception{
        Account destination = src;
        Thread t = new Thread(new OperatorTransfer(src, destination, amount));
        t.start();
        t.join();

        assertTrue(destination.getBalance() == srcBalance);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfAnyAccountIsNull() throws Exception {

        Thread t  = new Thread(new OperatorTransfer(src, null, amount));
        t.start();
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfAmountIsNegative() {
        Thread t = new Thread(new OperatorTransfer(src, dest, -1));
        t.start();
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfSourceDoesntHaveEnoughMoney() {
        Thread t = new Thread(new OperatorTransfer(src, dest, srcBalance + 1));
        t.start();
    }
}
