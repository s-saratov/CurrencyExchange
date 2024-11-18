package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class CustCurrency {

    private final String currencyCode;
    private final String currencyName;
    private LocalDateTime timestamp = null;

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

    // только геттеры, так как валюта неизменяемая
    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustCurrency currency = (CustCurrency) o;
        return currencyCode.equals(currency.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyCode='" + currencyCode + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}