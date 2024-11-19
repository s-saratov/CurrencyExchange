package model;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustCurrency that = (CustCurrency) o;
        return Objects.equals(currencyCode, that.currencyCode) && Objects.equals(currencyName, that.currencyName) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(currencyCode); // Хэш-код для currencyCode
        result = 31 * result + Objects.hashCode(currencyName); // Хэш-код для currencyName
        result = 31 * result + Objects.hashCode(timestamp); // Хэш-код для timestamp
        return result;
    }

    @Override
    public String toString() {
        return "CustCurrency" +
                "currencyCode='" + currencyCode + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", timestamp=" + timestamp
                ;
    }
}
