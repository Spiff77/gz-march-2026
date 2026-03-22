package io.treelevel.training.ex1;

/**
 * Exercise 1 — BankAccount (CORRECTION)
 */
public class BankAccount {

    private final String owner;
    private double balance;

    public BankAccount(String owner) {
        this.owner   = owner;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("ERROR: Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited £%.2f → Balance: £%.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("ERROR: Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("ERROR: Insufficient funds.");
            return;
        }
        balance -= amount;
        System.out.printf("Withdrew £%.2f → Balance: £%.2f%n", amount, balance);
    }

    public double getBalance() { return balance; }
    public String getOwner()   { return owner; }

    @Override
    public String toString() {
        return String.format("%s — Balance: £%.2f", owner, balance);
    }
}
