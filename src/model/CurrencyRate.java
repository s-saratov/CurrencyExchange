package model;

import java.time.LocalDateTime;
import java.util.Map;

public class CurrencyRate {

    // Поля
    private LocalDateTime timestamp;    // дата и время введения
    private double currencyRate;        // курс валюты

    public CurrencyRate(LocalDateTime timestamp, double currencyRate) {
        this.timestamp = timestamp;
        this.currencyRate = currencyRate;
    }

    public CurrencyRate(double currencyRate) {
        this.timestamp = LocalDateTime.now();
        this.currencyRate = currencyRate;
    }

    // Геттеры


    public double getCurrencyRate() {
        return currencyRate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // TODO: добавить сеттеры (при необходимости)

}
