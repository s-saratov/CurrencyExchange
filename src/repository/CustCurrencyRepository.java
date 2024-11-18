package repository;

import java.math.BigDecimal;
import java.util.Currency;

public interface CustCurrencyRepository {

    //CREATE
    boolean addCurrency(String currencyCode, String currencyName);

    //READ
    Currency getCurrencyByCode(String currencyCode);

    //UPDATE
    boolean  updateCurrency(String currencyCode, String currencyName);

    //DELETE
    boolean  deleteCurrency(String fromCurrency, String toCurrency, BigDecimal rate);


}
