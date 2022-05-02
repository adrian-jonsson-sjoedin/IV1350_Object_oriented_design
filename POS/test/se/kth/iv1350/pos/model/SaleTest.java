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
    private ItemDTO anotherItemDummy;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        itemDummy = new ItemDTO("dummy", 9999, 99.99, 99);
        anotherItemDummy = new ItemDTO("Another dummy", 8888, 89.99, 89);
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
        assertEquals(expected, actual, "Quantity did not update correctly");
    }

    @Test
    public void testAddNewItem() {
        sale.addItemToBasket(anotherItemDummy, 1);
        int expectedBasketSize = 2;
        int actualBasketSize = sale.getBasket().size();
        assertEquals(expectedBasketSize, actualBasketSize, "Item was not added to basket");
    }

    @Test
    public void testCanRetrieveItemName() {
        String expected = "dummy";
        String actual = sale.getBasket().get(0).getItemName();
        assertEquals(expected, actual, "Item name did not match");
    }

    @Test
    public void testCanRetrieveItemPrice() {
        double expected = 99.99;
        double actual = sale.getBasket().get(0).getItemPrice();
        assertEquals(expected, actual, "Item price did not match");
    }

    @Test
    public void testCanRetrieveItemVatRate() {
        double expected = 99;
        double actual = sale.getBasket().get(0).getItemVatRate();
        assertEquals(expected, actual, "Item VAT rate did not match");
    }

    @Test
    public void testCanRetrieveItemEanCode() {
        double expected = 9999;
        double actual = sale.getBasket().get(0).getItemEanCode();
        assertEquals(expected, actual, "Item's EAN code matched");
    }
}