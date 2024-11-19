package repository;

/**
 * Интерфейс для управления кастомными валютами.
 */

import model.CustCurrency;

import java.util.Map;

public interface CustCurrencyRepository {

    //----------------------CREATE-------------------------
    /**
     * Добавляет валюту.
     *
     * @param currencyCode Код валюты (например, "EUR").
     * @param currencyName Название валюты.
     * @return true, если валюта успешно добавлена; false, если валюта уже существует.
     */
    boolean addCurrency(String currencyCode, String currencyName);

    //----------------------READ--------------------------
    /**
     * Получает валюту по её коду.
     *
     * @param currencyCode Код валюты (например, "EUR").
     * @return Объект {@link CustCurrency}, если валюта найдена, иначе null.
     */
    CustCurrency getCurrencyByCode(String currencyCode);

    /**
     * Получает все доступные валюты.
     *
     * @return Карта всех валют, где ключ - код валюты, значение - объект {@link CustCurrency}.
     */
    Map<String, CustCurrency> getAllCurrencies();

    //----------------------UPDATE-----------------------
    /**
     * Обновляет данные существующей валюты.
     *
     * @param currencyCode Код валюты (например, "EUR").
     * @param currencyName Новое название валюты.
     * @return true, если обновление успешно; false, если валюта не найдена.
     */
    boolean  updateCurrency(String currencyCode, String currencyName);

    //-----------------------DELETE-------------------------
    /**
     * Удаляет валюту по её коду.
     *
     * @param currencyCode Код валюты, которую нужно удалить.
     * @return true, если валюта успешно удалена; false, если валюта не найдена.
     */
    boolean deleteCurrency(String currencyCode);

}
