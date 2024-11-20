import repository.CurrencyRateRepositoryImpl;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class CurrencyExchangeAppRATE {
    public static void main(String[] args) {

    // ==================ЗАГОТОВКА ДЛЯ ВЬЮХИ КУРС ВАЛЮТ=========================
        // Создаем экземпляр репозитория
        CurrencyRateRepositoryImpl repository = new CurrencyRateRepositoryImpl();

        // 1. Проверка добавления нового курса валюты
        repository.setCurrencyRate("GBP", BigDecimal.valueOf(0.87));
        System.out.println("Добавлен курс GBP: " + repository.getCurrencyRate("GBP"));

        // 2. Получение всех курсов валют
        Map<String, BigDecimal> allRates = repository.getAllCurrencyRates();
        System.out.println("Все доступные курсы: " + allRates);

        // 3. Обновление существующего курса
        repository.updateCurrencyRate("USD", BigDecimal.valueOf(1.15));
        System.out.println("Обновленный курс USD: " + repository.getCurrencyRate("USD"));

        // 4. Вычисление кросс-курса между PLN и USD
        BigDecimal crossRate = repository.calculateCrossRate("PLN", "USD");
        System.out.println("Кросс-курс PLN к USD: " + crossRate);

        // 5. Удаление курса валюты
        boolean isDeleted = repository.deleteCurrencyRate("GBP");
        System.out.println("Курс GBP удален: " + isDeleted);

        // 6. Проверка, что курс удален
        try {
            System.out.println("Курс GBP: " + repository.getCurrencyRate("GBP"));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

}