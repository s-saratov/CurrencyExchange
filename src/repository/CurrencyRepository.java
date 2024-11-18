package repository;

import java.math.BigDecimal;
import java.util.Currency;

public interface CurrencyRepository {

    //CREATE
    boolean addCurrency(String currencyCode, String currencyName);

    //READ
    Currency findCurrencyByCode(String currencyCode);

    //UPDATE
    boolean  updateCurrency(String currencyCode, String currencyName);

    //DELETE
    boolean  deleteCurrency(String fromCurrency, String toCurrency, BigDecimal rate);


}
