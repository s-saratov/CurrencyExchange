package model;

import java.time.LocalDateTime;

public class Currency {

    // Поля

    private String currencyCode;    // Код валюты
    private String currencyName;    // Наименование
    private CurrencyRate rate;      // Курс к евро

    // Конструктор

    public Currency(String code, String currencyName, double rate) {
        this.currencyCode = code;
        this.currencyName = currencyName;
        this.rate = new CurrencyRate(rate);
        // TODO: написать логику сохранения курса валют при создании объекта
    }

    public Currency(String code, String currencyName, double rate, LocalDateTime timestamp) {
        this.currencyCode = code;
        this.currencyName = currencyName;
        this.rate = new CurrencyRate(timestamp, rate);
        // TODO: написать логику сохранения курса валют при создании объекта
    }

    // Геттеры и сеттеры


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public CurrencyRate getRate() {
        return rate;
    }

    public void setRate(CurrencyRate rate) {
        this.rate = rate;
    }
}