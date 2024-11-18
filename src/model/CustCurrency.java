package model;

import java.time.LocalDateTime;

public class CustCurrency {

    private String сurrencyСode;
    private String currencyName;
    private LocalDateTime timestamp;

    //constructor
    public CustCurrency(String code, String currencyName) {
        this.сurrencyСode = code;
        this.currencyName = currencyName;
        this.timestamp = LocalDateTime.now();
    }

    //getters
    public String getСurrencyСode() {
        return сurrencyСode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //setters
    public void setСurrencyСode(String сurrencyСode) {
        this.сurrencyСode = сurrencyСode;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
