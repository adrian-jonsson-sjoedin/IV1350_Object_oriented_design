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

    /**
     * This method adds the item and the specified quantity of said item to the basket.
     *
     * @param item The item to be added to the basket
     * @param quantity How many of said item that should be added.
     */
    public void addItemToBasket(ItemDTO item, int quantity) {
        basket.add(new ItemInBasket(item, quantity));
    }
    
}
