package repository;

import model.CustCurrency;

import java.math.BigDecimal;
import java.util.*;

/**
 * Класс для управления кастомными валютами (EUR, USD, PLN).
 */

public class CustCurrencyRepositoryImpl implements CustCurrencyRepository {

    // Хранилище валют: ключ - код валюты, значение - объект валюты
    private Map<String, CustCurrency> currencyMap;
    private String currencyCode;
    private String currencyName;

    /**
     * Конструктор, который инициализирует предопределенные валюты.
     */
    public CustCurrencyRepositoryImpl() {
        Map<String, CustCurrency> initialCurrencies = new HashMap<>();
        initialCurrencies.put("EUR", new CustCurrency("EUR", "Euro"));
        initialCurrencies.put("USD", new CustCurrency("USD", "US Dollar"));
        initialCurrencies.put("PLN", new CustCurrency("PLN", "Polish Zloty"));

        this.currencyMap = initialCurrencies;
    }

    @Override
    public boolean addCurrency(String currencyCode, String currencyName) {
        if (currencyCode == null || currencyName == null) {
            throw new IllegalArgumentException("Currency code and name cannot be null.");
        }
        if (currencyCode.length() != 3) {
            throw new IllegalArgumentException("Currency code must be exactly 3 characters.");
        }
        if (currencyMap.containsKey(currencyCode.toUpperCase())) {
            return false; // Валюта уже существует
        }
        CustCurrency newCurrency = new CustCurrency(currencyCode.toUpperCase(), currencyName);
        currencyMap.put(currencyCode.toUpperCase(), newCurrency);
        return true;
    }

    @Override
    public CustCurrency getCurrencyByCode(String currencyCode) {
        return currencyMap.get(currencyCode.toUpperCase());
    }

    @Override
    public Map<String, CustCurrency> getAllCurrencies() {
        return new HashMap<>(currencyMap);
    }

    @Override
    public boolean updateCurrency(String currencyCode, String currencyName) {
        String upperCaseCode = currencyCode.toUpperCase();
        CustCurrency existingCurrency = currencyMap.get(upperCaseCode);
        if (existingCurrency == null) {
            return false; // Валюта не найдена
        }
        currencyMap.put(upperCaseCode, new CustCurrency(upperCaseCode, currencyName));
        return true;
    }

    @Override
    public boolean deleteCurrency(String currencyCode) {
        return currencyMap.remove(currencyCode.toUpperCase()) != null;
    }
}