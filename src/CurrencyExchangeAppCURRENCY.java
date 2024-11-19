import repository.CurrencyRateRepositoryImpl;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class CurrencyExchangeAppCURRENCY {
    public static void main(String[] args) {

        // ==================ЗАГОТОВКА ДЛЯ ВЬЮХИ ВАЛЮТА=========================
        // Создание экземпляра репозитория курсов валют
        CurrencyRateRepositoryImpl currencyRateRepository = new CurrencyRateRepositoryImpl();

        // 1. Показать все доступные курсы валют
        System.out.println("Текущие курсы валют:");
        printCurrencyRates(currencyRateRepository.getAllCurrencyRates());

        // 2. Получить курс для конкретной валюты
        try {
            String currencyCode = "USD";
            BigDecimal usdRate = currencyRateRepository.getCurrencyRate(currencyCode);
            System.out.println("\nКурс для валюты " + currencyCode + ": " + usdRate);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // 3. Добавить новый курс валюты
        System.out.println("\nДобавление курса валюты GBP...");
        boolean added = currencyRateRepository.setCurrencyRate("GBP", BigDecimal.valueOf(0.85));
        if (added) {
            System.out.println("Курс валюты GBP успешно добавлен.");
        }
        printCurrencyRates(currencyRateRepository.getAllCurrencyRates());

        // 4. Обновить курс существующей валюты
        System.out.println("\nОбновление курса валюты USD...");
        boolean updated = currencyRateRepository.updateCurrencyRate("USD", BigDecimal.valueOf(1.2));
        if (updated) {
            System.out.println("Курс валюты USD успешно обновлен.");
        }
        printCurrencyRates(currencyRateRepository.getAllCurrencyRates());

        // 5. Вычислить кросс-курс между двумя валютами
        try {
            String sourceCurrency = "PLN";
            String targetCurrency = "USD";
            BigDecimal crossRate = currencyRateRepository.calculateCrossRate(targetCurrency, sourceCurrency);
            System.out.println("\nКросс-курс " + targetCurrency + " к " + sourceCurrency + ": " + crossRate);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // 6. Удалить курс валюты
        System.out.println("\nУдаление курса валюты PLN...");
        boolean deleted = currencyRateRepository.deleteCurrencyRate("PLN");
        if (deleted) {
            System.out.println("Курс валюты PLN успешно удален.");
        }
        printCurrencyRates(currencyRateRepository.getAllCurrencyRates());
    }

    // Вспомогательный метод для вывода курсов валют
    private static void printCurrencyRates(Map<String, BigDecimal> currencyRates) {
        if (currencyRates.isEmpty()) {
            System.out.println("Нет доступных курсов валют.");
        } else {
            currencyRates.forEach((currency, rate) ->
                    System.out.println("Валюта: " + currency + " -> Курс: " + rate));
        }
    }

}