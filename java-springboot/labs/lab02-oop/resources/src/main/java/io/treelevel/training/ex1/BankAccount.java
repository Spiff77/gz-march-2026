package io.treelevel.training.ex1;

/**
 * Exercise 1 — BankAccount
 *
 * Model a bank account with proper encapsulation.
 * All fields are private. Business logic lives inside the methods.
 */
public class BankAccount {

    private final String owner;
    private double balance;

    public BankAccount(String owner) {
        this.owner   = owner;
        this.balance = 0.0;
    }

    /**
     * Adds amount to balance.
     * Prints an error and returns early if amount <= 0.
     */
    public void deposit(double amount) {
        // TODO: validate amount, then add to balance and print result
    }

    /**
     * Deducts amount from balance.
     * Prints an error and returns early if amount <= 0 or amount > balance.
     */
    public void withdraw(double amount) {
        // TODO: validate amount and sufficient funds, then deduct and print result
    }

    public double getBalance() { return balance; }
    public String getOwner()   { return owner; }

    @Override
    public String toString() {
        // TODO: return e.g. "Alice — Balance: £0.00"
        return "";
    }
}
