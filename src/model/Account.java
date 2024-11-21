package model;

public class Account {
    private String currencyCode; // Код валюты (например, USD, EUR)
    private double balance; // Баланс счета

    public Account(String currencyCode, double balance) {
        this.currencyCode = currencyCode;
        this.balance = balance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }
}