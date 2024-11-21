package model;

import java.time.LocalDateTime;

public class Transaction {
    private String fromCurrency; // Валюта отправителя
    private String toCurrency; // Валюта получателя
    private double amount; // Сумма
    private double rate; // Курс обмена
    private LocalDateTime timestamp; // Время транзакции

    public Transaction(String fromCurrency, String toCurrency, double amount, double rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.rate = rate;
        this.timestamp = LocalDateTime.now();
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public double getRate() {
        return rate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}