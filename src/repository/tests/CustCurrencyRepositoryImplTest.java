/*package repository.tests;

import model.CustCurrency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CustCurrencyRepositoryImpl;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustCurrencyRepositoryImplTest {

    private CustCurrencyRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new CustCurrencyRepositoryImpl();
    }

    // =========================== TEST: ADD CURRENCY ===========================

    @Test
    void testAddCurrency_Success() {
        boolean added = repository.addCurrency("GBP", "British Pound");
        CustCurrency gbp = repository.getCurrencyByCode("GBP");

        assertAll("Currency addition checks",
                () -> assertTrue(added, "Currency should be added successfully."),
                () -> assertNotNull(gbp, "GBP should exist in the repository."),
                () -> assertEquals("British Pound", gbp.getCurrencyName(), "Currency name should match.")
        );
    }

    @Test
    void testAddCurrency_AlreadyExists() {
        boolean added = repository.addCurrency("EUR", "Euro");
        assertFalse(added, "Adding existing currency should fail.");
    }

    @Test
    void testAddCurrency_InvalidCode() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> repository.addCurrency("EURO", "Euro"));
        assertEquals("Currency code must be exactly 3 characters.", exception.getMessage());
    }

    @Test
    void testAddCurrency_NullValues() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> repository.addCurrency(null, "Euro"));
        assertEquals("Currency code and name cannot be null.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> repository.addCurrency("EUR", null));
        assertEquals("Currency code and name cannot be null.", exception.getMessage());
    }

    // =========================== TEST: GET CURRENCY ===========================

    @Test
    void testGetCurrencyByCode_Found() {
        CustCurrency eur = repository.getCurrencyByCode("EUR");

        assertAll("EUR currency checks",
                () -> assertNotNull(eur, "EUR should exist in the repository."),
                () -> assertEquals("Euro", eur.getCurrencyName(), "Currency name should match.")
        );
    }

    @Test
    void testGetCurrencyByCode_NotFound() {
        CustCurrency gbp = repository.getCurrencyByCode("GBP");
        assertNull(gbp, "Non-existent currency should return null.");
    }

    // ======================== TEST: GET ALL CURRENCIES =========================

    @Test
    void testGetAllCurrencies_ContentValidation() {
        Map<String, CustCurrency> currencies = repository.getAllCurrencies();

        assertAll("Currencies validation",
                () -> assertEquals(3, currencies.size(), "Repository should contain 3 predefined currencies."),
                () -> assertTrue(currencies.containsKey("EUR"), "Repository should contain EUR."),
                () -> assertEquals("Euro", currencies.get("EUR").getCurrencyName(), "EUR name should match."),
                () -> assertTrue(currencies.containsKey("USD"), "Repository should contain USD."),
                () -> assertEquals("US Dollar", currencies.get("USD").getCurrencyName(), "USD name should match."),
                () -> assertTrue(currencies.containsKey("PLN"), "Repository should contain PLN."),
                () -> assertEquals("Polish Zloty", currencies.get("PLN").getCurrencyName(), "PLN name should match.")
        );
    }

    @Test
    void testGetAllCurrencies_Immutability() {
        Map<String, CustCurrency> currencies = repository.getAllCurrencies();
        currencies.remove("EUR");

        // Исходный репозиторий не должен быть изменен
        assertNotNull(repository.getCurrencyByCode("EUR"), "EUR should still exist in the repository.");
        assertEquals(3, repository.getAllCurrencies().size(), "Repository should still contain 3 currencies.");
    }

    // =========================== TEST: UPDATE CURRENCY ===========================

    @Test
    void testUpdateCurrency_Success() {
        boolean updated = repository.updateCurrency("USD", "United States Dollar");
        CustCurrency usd = repository.getCurrencyByCode("USD");

        assertAll("Update validation",
                () -> assertTrue(updated, "Currency should be updated successfully."),
                () -> assertNotNull(usd, "USD should still exist in the repository."),
                () -> assertEquals("United States Dollar", usd.getCurrencyName(), "Updated name should match."),
                () -> assertEquals("USD", usd.getCurrencyCode(), "Currency code should remain the same.")
        );
    }

    @Test
    void testUpdateCurrency_NotFound() {
        boolean updated = repository.updateCurrency("GBP", "British Pound");
        assertFalse(updated, "Updating non-existent currency should fail.");
    }

    // =========================== TEST: DELETE CURRENCY ===========================

    @Test
    void testDeleteCurrency_Success() {
        boolean deleted = repository.deleteCurrency("PLN");
        CustCurrency pln = repository.getCurrencyByCode("PLN");

        assertAll("Deletion validation",
                () -> assertTrue(deleted, "Currency should be deleted successfully."),
                () -> assertNull(pln, "Deleted currency should no longer exist.")
        );
    }

    @Test
    void testDeleteCurrency_NotFound() {
        boolean deleted = repository.deleteCurrency("GBP");
        assertFalse(deleted, "Deleting non-existent currency should fail.");
    }

    @Test
    void testDeleteCurrency_AlreadyDeleted() {
        repository.deleteCurrency("PLN");
        boolean secondDelete = repository.deleteCurrency("PLN");

        assertFalse(secondDelete, "Deleting already deleted currency should fail.");
    }

}


 */