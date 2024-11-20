package repository.tests;
/**
 * @author olgakharina
 * @date 18.11.24
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CurrencyRateRepositoryImpl;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateRepositoryImplTest {

    private CurrencyRateRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        // Инициализация репозитория перед каждым тестом
        repository = new CurrencyRateRepositoryImpl();
    }

    @Test
    void testSetExchangeRate_Success() {
        boolean added = repository.setCurrencyRate("GBP", BigDecimal.valueOf(0.87));
        assertTrue(added, "Курс GBP должен быть успешно добавлен.");
        BigDecimal rate = repository.getCurrencyRate("GBP");
        assertEquals(BigDecimal.valueOf(0.87), rate, "Курс GBP должен быть равен 0.87.");
    }

    @Test
    void testSetExchangeRate_InvalidArguments() {
        assertThrows(IllegalArgumentException.class, 
                     () -> repository.setCurrencyRate(null, BigDecimal.valueOf(1.0)),
                     "Код валюты не может быть null.");
        assertThrows(IllegalArgumentException.class, 
                     () -> repository.setCurrencyRate("USD", null),
                     "Курс валюты не может быть null.");
    }

    @Test
    void testGetCurrencyRate_Success() {
        BigDecimal usdRate = repository.getCurrencyRate("USD");
        assertEquals(BigDecimal.valueOf(1.1), usdRate, "Курс USD должен быть равен 1.1.");
    }

    @Test
    void testGetCurrencyRate_NotFound() {
        assertThrows(IllegalArgumentException.class, 
                     () -> repository.getCurrencyRate("GBP"),
                     "Курс для отсутствующей валюты должен вызывать исключение.");
    }

    @Test
    void testGetAllCurrencyRates() {
        Map<String, BigDecimal> allRates = repository.getAllCurrencyRates();
        assertEquals(3, allRates.size(), "Должно быть 3 предопределенных курса.");
        assertTrue(allRates.containsKey("EUR"), "Курс EUR должен присутствовать.");
        assertTrue(allRates.containsKey("USD"), "Курс USD должен присутствовать.");
        assertTrue(allRates.containsKey("PLN"), "Курс PLN должен присутствовать.");
    }

    @Test
    void testUpdateCurrencyRate_Success() {
        boolean updated = repository.updateCurrencyRate("USD", BigDecimal.valueOf(1.15));
        assertTrue(updated, "Курс USD должен быть успешно обновлен.");
        BigDecimal updatedRate = repository.getCurrencyRate("USD");
        assertEquals(BigDecimal.valueOf(1.15), updatedRate, "Курс USD должен быть равен 1.15.");
    }

    @Test
    void testUpdateCurrencyRate_NotFound() {
        assertThrows(IllegalArgumentException.class, 
                     () -> repository.updateCurrencyRate("GBP", BigDecimal.valueOf(0.90)),
                     "Обновление курса для отсутствующей валюты должно вызывать исключение.");
    }

    @Test
    void testDeleteCurrencyRate_Success() {
        boolean deleted = repository.deleteCurrencyRate("PLN");
        assertTrue(deleted, "Курс PLN должен быть успешно удален.");
        assertThrows(IllegalArgumentException.class, 
                     () -> repository.getCurrencyRate("PLN"),
                     "Удаленный курс должен быть недоступен.");
    }

    @Test
    void testDeleteCurrencyRate_NotFound() {
        boolean deleted = repository.deleteCurrencyRate("GBP");
        assertFalse(deleted, "Удаление отсутствующей валюты должно возвращать false.");
    }

    @Test
    void testCalculateCrossRate_Success() {
        BigDecimal crossRate = repository.calculateCrossRate("PLN", "USD");
        assertNotNull(crossRate, "Кросс-курс PLN к USD должен быть рассчитан.");
        assertEquals(BigDecimal.valueOf(4.090909), crossRate, "Кросс-курс PLN к USD должен быть 4.090909.");
    }

    @Test
    void testCalculateCrossRate_InvalidArguments() {
        assertThrows(IllegalArgumentException.class, 
                     () -> repository.calculateCrossRate("GBP", "USD"),
                     "Кросс-курс для отсутствующей валюты должен вызывать исключение.");
        assertThrows(IllegalArgumentException.class, 
                     () -> repository.calculateCrossRate("PLN", "GBP"),
                     "Кросс-курс для отсутствующей валюты должен вызывать исключение.");
    }
}