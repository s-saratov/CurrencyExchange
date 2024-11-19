package model;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CurrencyRate {

    private Currency currency;
    private double currencyRate;
    private LocalDateTime timestamp;

    //constructor
    public CurrencyRate(Currency currency, double currencyRate, LocalDateTime timestamp) {
        this.currency = currency;
        this.currencyRate = currencyRate;
        this.timestamp = LocalDateTime.now();
    }

    //getters
    public Currency getCurrency() {
        return currency;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //setters
    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    //methods
    @Override
    public String toString() {
        return "\nCurrencyRate{" +
                "currency: " + currency +
                ", currencyRate: " + currencyRate +
                ", timestamp :" + timestamp +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyRate that)) return false;

        return Double.compare(currencyRate, that.currencyRate) == 0 && Objects.equals(currency, that.currency) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(currency);
        result = 31 * result + Double.hashCode(currencyRate);
        result = 31 * result + Objects.hashCode(timestamp);
        return result;
    }


}



