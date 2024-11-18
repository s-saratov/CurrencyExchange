package repository;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRateRepositoryImpl implements CurrencyRateRepository {

    Map<String, Double> currencyRates;

    //constructor
    public CurrencyRateRepositoryImpl() {
        this.currencyRates = new HashMap<>();
    }

    //methods
    @Override
    public boolean setExchangeRate(String currencyCode, BigDecimal rate) {
        return false;
    }

    @Override
    public Map<Currency, BigDecimal> getCurrencyRates(String currencyCode) {
        return Map.of();
    }

    @Override
    public boolean deleteCeurrencyRate(String currencyCode) {
        return false;
    }
}
