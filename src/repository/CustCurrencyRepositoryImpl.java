package repository;

import model.CustCurrency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustCurrencyRepositoryImpl implements CustCurrencyRepository {

   private List<CustCurrency> currencyList;

    //constructor
    public CustCurrencyRepositoryImpl() {

        CustCurrency eur = new CustCurrency("EUR", "Euro");
        CustCurrency usd = new CustCurrency("USD", "Dollar USA");
        CustCurrency pln = new CustCurrency("PLN", "Polish zloty");

        this.currencyList = new ArrayList<>(List.of(eur, usd, pln));
    }

    public List<CustCurrency> getCurrencyList() {
        return currencyList;
    }

    //methods
    @Override
    public boolean addCurrency(String currencyCode, String currencyName) {
        return false;
    }

    @Override
    public CustCurrency getCurrencyByCode(String currencyCode) {
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
