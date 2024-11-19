package repository;

import model.CurrencyRate;

import java.math.BigDecimal;
import java.util.*;

/**
 * Класс для управления курсами валют.
 * Хранит курсы валют в виде: сколько единиц целевой валюты можно получить за 1 единицу базовой валюты.
 */
public class CurrencyRateRepositoryImpl implements CurrencyRateRepository {

    // Хранилище курсов валют: ключ - код валюты, значение - курс обмена
    private  Map<String, BigDecimal> currencyRates;

    public CurrencyRateRepositoryImpl() {
        this.currencyRates = new HashMap<>();

        // Инициализация базовых валют и их курсов относительно базовой валюты (например, EUR)
        currencyRates.put("EUR", BigDecimal.valueOf(1.0));   // Базовая валюта
        currencyRates.put("USD", BigDecimal.valueOf(1.1));
        currencyRates.put("PLN", BigDecimal.valueOf(4.5));
    }

    /**
     * Добавляет или обновляет курс валюты.
     *
     * @param currencyCode Код валюты (например, "USD").
     * @param rate         Курс валюты (например, 1.1).
     * @return true, если валюта добавлена или обновлена успешно.
     */
    @Override
    public boolean setCurrencyRate(String currencyCode, BigDecimal rate) {
        if (currencyCode == null || rate == null) {
            throw new IllegalArgumentException("Код валюты и курс не могут быть null.");
        }
        currencyRates.put(currencyCode.toUpperCase(), rate);
        return true;
    }
    /**
     * Получает курс валюты по её коду.
     *
     * @param currencyCode Код валюты (например, "USD").
     * @return Курс валюты (например, 1.1), либо null, если валюта не найдена.
     */
    public BigDecimal getCurrencyRate(String currencyCode) {
        BigDecimal rate = currencyRates.get(currencyCode.toUpperCase());
        if (rate == null) {
            throw new IllegalArgumentException("Курс для валюты '" + currencyCode + "' не найден.");
        }
        return rate;
    }

    /**
     * Возвращает все доступные курсы валют.
     *
     * @return Копия карты курсов валют.
     */
    public Map<String, BigDecimal> getAllCurrencyRates() {
        return new HashMap<>(currencyRates);
    }

    /**
     * Обновляет курс валюты.
     *
     * @param currencyCode Код валюты.
     * @param newRate      Новый курс.
     * @return true, если обновление успешно.
     */
    @Override
    public boolean updateCurrencyRate(String currencyCode, BigDecimal newRate) {
        if (currencyCode == null || newRate == null) {
            throw new IllegalArgumentException("Код валюты и новый курс не могут быть null.");
        }
        if (!currencyRates.containsKey(currencyCode.toUpperCase())) {
            throw new IllegalArgumentException("Курс для валюты '" + currencyCode + "' не найден.");
        }
        currencyRates.put(currencyCode.toUpperCase(), newRate);
        return true;
    }

    /**
     * Удаляет курс валюты по её коду.
     *
     * @param currencyCode Код валюты (например, "USD").
     * @return true, если валюта успешно удалена, иначе false.
     */
    public boolean deleteCurrencyRate(String currencyCode) {
        if (currencyCode == null) {
            throw new IllegalArgumentException("Код валюты не может быть null.");
        }
        return currencyRates.remove(currencyCode.toUpperCase()) != null;
    }

    /**
     * Вычисляет кросс-курс между двумя валютами.
     *
     * @param targetCurrency Код целевой валюты (например, "USD").
     * @param sourceCurrency Код исходной валюты (например, "PLN").
     * @return Кросс-курс между целевой и исходной валютами.
     * @throws IllegalArgumentException если одна из валют отсутствует.
     */
    public BigDecimal calculateCrossRate(String targetCurrency, String sourceCurrency) {
        BigDecimal targetRate = currencyRates.get(targetCurrency.toUpperCase());
        BigDecimal sourceRate = currencyRates.get(sourceCurrency.toUpperCase());

        if (targetRate == null || sourceRate == null) {
            throw new IllegalArgumentException("Одна или обе валюты отсутствуют в репозитории.");
        }

        return targetRate.divide(sourceRate, 6, BigDecimal.ROUND_HALF_UP); // Точность до 6 знаков
    }

}