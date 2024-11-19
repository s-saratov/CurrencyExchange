package model;

import java.time.LocalDateTime;

public class CustCurrency {

    private String currencyCode;
    private String currencyName;
    private LocalDateTime timestamp;

    //constructor
    public CustCurrency(String code, String currencyName) {
        this.currencyCode = code;
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
