package repository;

import model.CurrencyRate;
import model.CustCurrency;

import java.util.Currency;
import java.math.BigDecimal;
import java.util.*;

public class CurrencyRateRepositoryImpl implements CurrencyRateRepository {

    private CustCurrencyRepositoryImpl currencyRepository;
    private List<CurrencyRate> currencyRateList;

    //constructor
    public CurrencyRateRepositoryImpl() {

    }

    public List<CurrencyRate> getCurrencyRateList() {
        return currencyRateList;
    }

    //methods
    @Override
    public boolean setExchangeRate(String currencyCode, BigDecimal rate) {
        return false;
    }

    @Override
    public BigDecimal getCurrencyRates(String currencyCode) {
        return BigDecimal.ZERO;
    }

    @Override
    public boolean deleteCurrencyRate(String currencyCode) {
        return false;
    }
}
