package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import model.CurrencyRate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CurrencyRate {

    private final String currencyCode;
    private BigDecimal exchangeRate;
    private final LocalDateTime timestamp;
    private CustCurrency custCurrency;
    private double currencyRate;

    // Конструктор
    public CurrencyRate(String currencyCode, BigDecimal exchangeRate) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            throw new IllegalArgumentException("Currency code cannot be null or empty.");
        }
        if (exchangeRate == null || exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Exchange rate must be greater than 0.");
        }
        this.currencyCode = currencyCode.toUpperCase();
        this.exchangeRate = exchangeRate;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public LocalDateTime getAddedDate() {
        return timestamp;
    }

    // Setters
    public void setExchangeRate(BigDecimal exchangeRate) {
        if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Exchange rate must be greater than 0.");
        }
        this.exchangeRate = exchangeRate;
    }
    @Override
    public String toString() {
        return "CurrencyRate" +
                "custCurrency=" + custCurrency +
                ", currencyRate=" + currencyRate +
                ", timestamp=" + timestamp;

    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(custCurrency);
        result = 31 * result + Double.hashCode(currencyRate);
        result = 31 * result + Objects.hashCode(timestamp);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRate that = (CurrencyRate) o;
        return Double.compare(currencyRate, that.currencyRate) == 0 && Objects.equals(custCurrency, that.custCurrency) && Objects.equals(timestamp, that.timestamp);
    }
}



