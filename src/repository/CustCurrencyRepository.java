package repository;

import model.CustCurrency;

import java.math.BigDecimal;

public interface CustCurrencyRepository {

    //CREATE
    boolean addCurrency(String currencyCode, String currencyName);

    //READ
    CustCurrency getCurrencyByCode(String currencyCode);

    //UPDATE
    boolean  updateCurrency(String currencyCode, String currencyName);

    //DELETE
    boolean  deleteCurrency(String fromCurrency, String toCurrency, BigDecimal rate);


}
