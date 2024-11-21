package repository;

import model.Currency;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    // Поля

    private Map<String, Currency> currencyMap;  // Карта валют

    // Конструктор

    public CurrencyRepositoryImpl() {
        // Создаём карту валют
        this.currencyMap = new HashMap<>();
        // Добавляем валюты по умолчанию
        this.addCurrency("EUR", "Евро", 1.0);
        this.addCurrency("USD", "Доллар США", 0.9477);
        this.addCurrency("PLN", "Польский злотый", 0.231);
    }

    // Геттеры и сеттеры

    public Map<String, Currency> getCurrencyMap() {
        return currencyMap;
    }

    // Методы

    // === CREATE ===

    // Добавляет валюту (с текущими датой и временем установки курса)
    @Override
    public boolean addCurrency(String currencyCode, String currencyName, double rate) {
        if (currencyCode == null || currencyName == null) {
            throw new IllegalArgumentException("Currency code and name cannot be null.");
        }
        if (currencyCode.length() != 3) {
            throw new IllegalArgumentException("Currency code must be exactly 3 characters.");
        }
        if (currencyMap.containsKey(currencyCode.toUpperCase())) {
            return false; // Валюта уже существует
        }
        Currency newCurrency = new Currency(currencyCode.toUpperCase(), currencyName, rate);
        currencyMap.put(currencyCode.toUpperCase(), newCurrency);
        return true;
    }

    // Добавляет валюту (с заданными датой и временем установки курса)
    @Override
    public boolean addCurrency(String currencyCode, String currencyName, double rate, String timestamp) {
        if (currencyCode == null || currencyName == null) {
            throw new IllegalArgumentException("Currency code and name cannot be null.");
        }
        if (currencyCode.length() != 3) {
            throw new IllegalArgumentException("Currency code must be exactly 3 characters.");
        }
        if (currencyMap.containsKey(currencyCode.toUpperCase())) {
            return false; // Валюта уже существует
        }
        Currency newCurrency = new Currency(currencyCode.toUpperCase(), currencyName, rate, LocalDateTime.parse(timestamp));
        currencyMap.put(currencyCode.toUpperCase(), newCurrency);
        return true;
    }

    // === READ ===

    // Возвращает карту всех валют
    @Override
    public Map<String, Currency> getAllCurrencies() {
        return new HashMap<>(currencyMap);
    }
}