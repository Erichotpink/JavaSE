package com.epam.javase.t01.usingsynchronized;

/**
 * Represent a request to transfer money between accounts.
 *
 * Created by aivanov on 3/20/2017.
 */
public final class OperatorTransfer implements Runnable {

    private final Account src;
    private final Account dest;
    private final int amount;


    public OperatorTransfer(Account src, Account dest, int amount){
        if (src == null || dest == null) {
            throw new IllegalArgumentException("Wrong value. Source or destination account is null");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Please specify only positive amount. Current amount: " + amount);
        }

        if (amount > src.getBalance()) {
            throw new IllegalArgumentException(String.format(
                    "The account with ID %d doesn't have enough money. Current balance: %d. Requested to withdraw: %d.",
                    src.getID(), src.getBalance(), amount));
        }

        this.src = src;
        this.dest = dest;
        this.amount = amount;
    };

    @Override
    public void run() {
        if(src.equals(dest) || amount == 0) {
            return;
        }

        Account srcAccount = null;
        Account destAccount = null;

        if (src.getID() > dest.getID()) {
            srcAccount = src;
            destAccount = dest;
        } else {
            srcAccount = dest;
            destAccount = src;
        }

        synchronized (srcAccount) {
            synchronized (destAccount) {
                src.withdraw(amount);
                dest.deposit(amount);
            }
        }
    }

}
