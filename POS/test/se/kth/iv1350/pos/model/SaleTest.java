package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the Sale class.
 */
class SaleTest {
    private Sale sale;
    private ItemDTO itemDummy;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        itemDummy = new ItemDTO("dummy", 9999, 99.99, 99);
        sale.addItemToBasket(itemDummy, 1);
    }

    @AfterEach
    void tearDown() {
        sale = null;
        itemDummy = null;
    }

    @Test
    public void testAddAlreadyExistingItemToBasket() {
        sale.addItemToBasket(itemDummy, 1);
        int expected = 2;
        int actual = sale.getBasket().get(0).getQuantity();
        assertEquals(expected,actual, "Quantity did not update correctly");
    }
}