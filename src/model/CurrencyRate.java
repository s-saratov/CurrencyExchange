package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class CurrencyRate {

    // Поля

    private Map<Currency, Double> currencyRates;    // карта курсов валют
    private LocalDateTime lastUpdated;              // дата и время последнего обновления курсов

    // Конструктор, который принимает одну валюту и её курс

    public CurrencyRate(Currency currency, Double rate, LocalDateTime lastUpdated) {
        this.currencyRates = new HashMap<>();
        this.currencyRates.put(currency, rate);
        this.lastUpdated = lastUpdated;
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

    // Методы

    // Добавляет новый курс валюты
    public void addCurrencyRate(Currency currency, Double rate) {
        this.currencyRates.put(currency, rate);
        this.lastUpdated = LocalDateTime.now(); // Обновляем время
    }

    // Отображает все курсы валют
    public void showRates() {
        System.out.println("Current currency rates (Last updated: " + lastUpdated + "):");
        currencyRates.forEach((currency, rate) ->
                System.out.println(currency + ": " + rate)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRate that = (CurrencyRate) o;
        return Objects.equals(currencyRates, that.currencyRates) && Objects.equals(lastUpdated, that.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyRates, lastUpdated);
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "currencyRates=" + currencyRates +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
