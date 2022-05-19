package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests that the InventoryDBUnreachable works as intended.
 */
class InventoryDBUnresponsiveExceptionTest {
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
    public void testExceptionShouldNotBeThrown() {
        int eanCode = 6880;
        Assertions.assertDoesNotThrow(() -> inventory.retrieveItem(eanCode), "An exception was thrown," +
                "which it should not");
    }

    @Test
    public void testExceptionIsThrown() {
        int dbFailureEanCode = 6969;
        assertThrows(InventoryDBUnresponsiveException.class, () -> inventory.retrieveItem(dbFailureEanCode),
                "An exception was not thrown");
    }
}