package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    private List<Currency> currencyList;

    //constructor
    public CurrencyRepositoryImpl() {
        this.currencyList = new ArrayList<>(List.of(
                new Currency("EUR", "Euro"),
                new Currency("USD", "Dollar USA"),
                new Currency("PLN", "Polish zloty")
        ));
    }

    //methods
    @Override
    public boolean addCurrency(String currencyCode, String currencyName) {
        return false;
    }

    @Override
    public Currency findCurrencyByCode(String currencyCode) {
        return null;
    }

    @Override
    public boolean updateCurrency(String currencyCode, String currencyName) {
        return false;
    }

    @Override
    public boolean deleteCurrency(String fromCurrency, String toCurrency, BigDecimal rate) {
        return false;
    }
}
