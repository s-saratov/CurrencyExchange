package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CurrencyRate {

    private Currency baseCurrency;
    private Currency targetCurrency;
    private double currencyRate;
    private LocalDateTime timestamp;

    //constructor
    public CurrencyRate(Currency baseCurrency, Currency targetCurrency, double currencyRate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.currencyRate = currencyRate;
        this.timestamp = LocalDateTime.now();
    }

    //getters
    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //setters
    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    //methods


    @Override
    public String toString() {
        return "CurrencyRate{" +
                "baseCurrency=" + baseCurrency +
                ", targetCurrency=" + targetCurrency +
                ", currencyRate=" + currencyRate +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyRate that)) return false;

        return Double.compare(currencyRate, that.currencyRate) == 0 && Objects.equals(baseCurrency, that.baseCurrency) && Objects.equals(targetCurrency, that.targetCurrency) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(baseCurrency);
        result = 31 * result + Objects.hashCode(targetCurrency);
        result = 31 * result + Double.hashCode(currencyRate);
        result = 31 * result + Objects.hashCode(timestamp);
        return result;
    }
}
