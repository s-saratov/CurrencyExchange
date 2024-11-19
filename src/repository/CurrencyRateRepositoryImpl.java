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

        this.currencyRepository = currencyRepository;
        CustCurrency eur = currencyRepository.getCurrencyByCode("EUR");
        CustCurrency usd = currencyRepository.getCurrencyByCode("USD");
        CustCurrency pln = currencyRepository.getCurrencyByCode("PLN");

        this.currencyRateList = new ArrayList<>(List.of(
               new CurrencyRate(eur, eur, 1.0),
               new CurrencyRate(eur, usd, 1.0552),
               new CurrencyRate(usd, eur, 0.9),
               new CurrencyRate(usd, usd, 1),
               new CurrencyRate(eur, pln, 4.32),
               new CurrencyRate(pln, eur, 0.345),
               new CurrencyRate(pln, pln, 1)
        ));

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
    public Map<Currency, BigDecimal> getCurrencyRates(String currencyCode) {
        return Map.of();
    }

    @Override
    public boolean deleteCurrencyRate(String currencyCode) {
        return false;
    }
}
