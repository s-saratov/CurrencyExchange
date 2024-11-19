package model;

import java.time.LocalDateTime;

public class CustCurrency {

    private String currencyCode;
    private String currencyName;
    private LocalDateTime timestamp;

    //constructor
    public CustCurrency(String currencyCode, String currencyName) {

        // проверяем, что код валюты не пустой и не более трех симвволов
        if (currencyCode == null || currencyCode.length() != 3) {
            throw new IllegalArgumentException("Currency code must be exactly 3 characters.");
        }
        if (currencyName == null || currencyName.isEmpty()) {
            throw new IllegalArgumentException("Currency name cannot be null or empty.");
        }
        // код валюты должен быть в верхнем регистре
        this.currencyCode = currencyCode.toUpperCase();
        this.currencyName = currencyName;
        this.timestamp = LocalDateTime.now();
    }

    //getters
    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //setters
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
