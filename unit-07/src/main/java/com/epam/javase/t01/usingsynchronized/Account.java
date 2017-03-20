package com.epam.javase.t01.usingsynchronized;

/**
 * Represnts a simple bank account.
 *
 * Created by aivanov on 3/20/2017.
 */
public class Account {

    private static volatile long initialID = 1;

    private final long id;
    private int balance;

    public Account(int balance) {
        this.balance = balance;
        this.id = initialID++;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount must be positive.");
            }
            balance += amount;
    }

    public void withdraw(int amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount must be positive.");
            }
            if (amount > balance) {
                throw new IllegalArgumentException(String.format(
                        "The account with ID %d doesn't have enough money. Current balance: %d. Requested to withdraw: %d.", id, balance, amount));
            }

            balance -= amount;
    }

    public long getID() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
