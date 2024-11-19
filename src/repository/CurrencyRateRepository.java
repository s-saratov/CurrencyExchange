package repository;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public interface CurrencyRateRepository {

    //CREATE
    boolean setExchangeRate(String currencyCode, BigDecimal rate);

    //READ
    BigDecimal getCurrencyRates(String currencyCode);

    /*  посмотреть курсы валют на определенную дату - ПОТОМ
        посмотреть историю изменения курса определенной валюты - ПОТОМ
     */

    //DELETE
    boolean deleteCurrencyRate(String currencyCode);






}
