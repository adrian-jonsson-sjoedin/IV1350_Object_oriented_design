package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Tha class that is handling the sale. Contains methods and fields necessary for one sale instance.
 *
 * @author Adrian Jonsson Sjoedin.
 */
public class Sale {
    private float runningTotalPrice;
    private float totalVatPrice;
    private float totalPrice;
    private List<ItemInBasket> basket = new ArrayList<>();

    /**
     * Creates an instance of sale.
     */
    public Sale() {
    }

    public List<ItemInBasket> getBasket() {
        return basket;
    }

    /**
     * This method adds the item and the specified quantity of said item to the basket.
     *
     * @param item     The item to be added to the basket.
     * @param quantity How many of said item that should be added.
     */
    public void addItemToBasket(ItemDTO item, int quantity) {
        if (itemAlreadyScanned(item)) {
            updateQuantityOfItemInBasket(item, quantity);
        } else {
            basket.add(new ItemInBasket(item, quantity));
        }
    }

    /**
     * Checks if an item is already added to the basket.
     *
     * @param item The item to check.
     * @return True if item is already in basket, otherwise false.
     */
    private boolean itemAlreadyScanned(ItemDTO item) {
        for (ItemInBasket itemInBasket : basket) {
            if (itemInBasket.getItemEanCode() == item.getEanCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the quantity of an item in the basket.
     *
     * @param item     The item that should be updated.
     * @param quantity The quantity we should add to the item already in the basket.
     */
    private void updateQuantityOfItemInBasket(ItemDTO item, int quantity) {
        for (ItemInBasket itemInBasket : basket) {
            if (itemInBasket.getItemEanCode() == item.getEanCode()) {
                itemInBasket.setQuantity(itemInBasket.getQuantity() + quantity);
            }
        }
    }
}
