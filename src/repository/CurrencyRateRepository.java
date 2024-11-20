package repository;

import model.CurrencyRate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public interface CurrencyRateRepository {
    //--------------------------CREATE-----------------------------
    /**
     * Устанавливает курс валюты.
     *
     * @param currencyCode Код валюты (например, "USD").
     * @param rate         Курс валюты.
     * @return true, если курс успешно добавлен или обновлен.
     */
    boolean setCurrencyRate(String currencyCode, BigDecimal rate);

    //-------------------------READ---------------------------------
    /**
     * Возвращает курс валюты.
     *
     * @param currencyRate Код валюты.
     * @return Объект CurrencyRate с курсом и датой добавления.
     */
    BigDecimal getCurrencyRate(String currencyRate);

    /**
     * Возвращает все курсы валют.
     *
     * @return Карта всех курсов валют.
     */
    Map<String, BigDecimal> getAllCurrencyRates();

     //-------------------------UPDATE----------------------------
    /**
     * Обновляет курс валюты.
     *
     * @param currencyCode Код валюты.
     * @param newRate      Новый курс.
     * @return true, если обновление успешно.
     */
    boolean updateCurrencyRate(String currencyCode, BigDecimal newRate);

    //------------------------------DELETE-----------------------------
    /**
     * Удаляет курс валюты.
     *
     * @param currencyCode Код валюты.
     * @return true, если курс успешно удален.
     */
    boolean deleteCurrencyRate(String currencyCode);

    //---------------------------------UTILS------------------------------
    /**
     * Вычисляет кросс-курс между двумя валютами.
     *
     * @param targetCurrency Код целевой валюты.
     * @param sourceCurrency Код исходной валюты.
     * @return Кросс-курс в BigDecimal.
     */
    BigDecimal calculateCrossRate(String targetCurrency, String sourceCurrency);

}
