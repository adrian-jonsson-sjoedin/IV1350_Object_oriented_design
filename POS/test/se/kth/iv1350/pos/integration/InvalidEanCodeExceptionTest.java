package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests that the InvalidEanCodeException class works asa intended.
 */
class InvalidEanCodeExceptionTest {
    ExternalInventorySystem inventory;

    @BeforeEach
    void setUp() {
        inventory = new ExternalInventorySystem();
    }

    @AfterEach
    void tearDown() {
        inventory = null;
    }

    @Test
    public void testItemExists() {
        int existingItemEanCode = 6880;
        assertDoesNotThrow(() -> inventory.retrieveItem(existingItemEanCode), "Throws an unexpected exception");
    }

    @Test
    public void testItemDoesNotExistException() {
        int invalidEanCode = 1;
        assertThrows(InvalidEanCodeException.class, () -> inventory.retrieveItem(invalidEanCode),
                "An exception was not thrown");
    }
}