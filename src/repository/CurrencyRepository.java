package repository;

import model.Currency;

import java.util.Map;

public interface CurrencyRepository {

    // Геттеры и сеттеры

    public Map<String, Currency> getCurrencyMap();

    // === CREATE ===

    // Добавляет валюту (с текущими датой и временем установки курса)
    public boolean addCurrency(String currencyCode, String currencyName, double rate);

    // Добавляет валюту (с заданными датой и временем установки курса)
    public boolean addCurrency(String currencyCode, String currencyName, double rate, String timestamp);

    // === READ ===

    // Возвращает карту всех валют
    public Map<String, Currency> getAllCurrencies();

}