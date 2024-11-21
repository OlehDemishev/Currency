package model;

public class Currency {
    private String code; // Код валюты (например, USD, EUR)
    private double rate; // Курс валюты

    public Currency(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}