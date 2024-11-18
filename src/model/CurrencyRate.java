package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

//Класс CurrencyRate представляет собой объект для хранения информации о курсе валют.
// Он включает базовую валюту, целевую валюту, курс и временную метку

public class CurrencyRate {

    private final CustCurrency baseCurrency;
    private final CustCurrency targetCurrency;
    private final BigDecimal currencyRate;
    private final LocalDateTime timestamp;

    public CurrencyRate(CustCurrency baseCurrency, CustCurrency targetCurrency, double currencyRate) {
        if (baseCurrency == null || targetCurrency == null) {
            throw new IllegalArgumentException("Currencies cannot be null.");
        }
        if ((currencyRate <= 0)) {
            throw new IllegalArgumentException("Currency rate must be greater than 0.");
        }

        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.currencyRate = BigDecimal.valueOf(currencyRate);
        this.timestamp = LocalDateTime.now();
    }

    public CustCurrency getBaseCurrency() {
        return baseCurrency;
    }

    public CustCurrency getTargetCurrency() {
        return targetCurrency;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyRate that)) return false;

        return currencyRate.compareTo(that.currencyRate) == 0 &&
                Objects.equals(baseCurrency, that.baseCurrency) &&
                Objects.equals(targetCurrency, that.targetCurrency);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(baseCurrency);
        result = 31 * result + Objects.hashCode(targetCurrency);
        result = 31 * result + currencyRate.hashCode(); // Использование BigDecimal.hashCode
        result = 31 * result + Objects.hashCode(timestamp);
        return result;
    }
}