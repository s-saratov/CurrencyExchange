package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//Класс CurrencyRate представляет собой объект для хранения информации о курсе валют.
// Он включает базовую валюту, целевую валюту, курс и временную метку.
public class CurrencyRate {

    private final Currency baseCurrency;
    private final Currency targetCurrency;
    private final BigDecimal currencyRate;
    private final LocalDateTime timestamp;

    public CurrencyRate(Currency baseCurrency, Currency targetCurrency, BigDecimal currencyRate) {
        if (baseCurrency == null || targetCurrency == null) {
            throw new IllegalArgumentException("Currencies cannot be null.");
        }
        if (currencyRate == null || currencyRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Currency rate must be greater than 0.");
        }

        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.currencyRate = currencyRate;
        this.timestamp = LocalDateTime.now();
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getTargetCurrency() {
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
