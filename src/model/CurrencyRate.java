package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class CurrencyRate {
    // Карта курсов валют
    private Map<Currency, Double> currencyRates;
    private LocalDateTime lastUpdated;          // Время последнего обновления курсов

    // Конструктор, который принимает одну валюту и её курс
    public CurrencyRate(Currency currency, Double rate) {
        this.currencyRates = new HashMap<>();
        this.currencyRates.put(currency, rate);
        this.lastUpdated = LocalDateTime.now();
    }

    // Геттеры
    public Map<Currency, Double> getCurrencyRates() {
        return currencyRates;
    }

    public Double getRate(Currency currency) {
        return currencyRates.get(currency);
    }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }


    // Сеттеры
    public void setCurrencyRates(Map<Currency, Double> currencyRates) {
        this.currencyRates = currencyRates;
        this.lastUpdated = LocalDateTime.now(); // Обновляем время
    }

    public void setRate(Currency currency, Double rate) {
        this.currencyRates.put(currency, rate);
        this.lastUpdated = LocalDateTime.now(); // Обновляем время
    }

    // Метод для добавления нового курса валюты
    public void addCurrencyRate(Currency currency, Double rate) {
        this.currencyRates.put(currency, rate);
        this.lastUpdated = LocalDateTime.now(); // Обновляем время
    }

    // Метод для отображения всех курсов
    public void showRates() {
        System.out.println("Current currency rates (Last updated: " + lastUpdated + "):");
        currencyRates.forEach((currency, rate) ->
                System.out.println(currency + ": " + rate)
        );
    }


}
