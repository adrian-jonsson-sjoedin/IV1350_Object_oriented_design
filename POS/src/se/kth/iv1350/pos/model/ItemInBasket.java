package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

/**
 * Represent a buyable item in the basket and the quantity of said item.
 */
public class ItemInBasket {
    private final ItemDTO item;
    private int quantity;

    /**
     * Creates an instance of the ItemInBasket that will be used by the {@link Sale Sale} class.
     * @param item The ItemDTO that will be used to create the new item
     * @param quantity How many of that item there is
     */
    public ItemInBasket(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemDTO getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemEanCode() {
        return item.getEanCode();
    }

    public String getItemName() {
        return item.getItemName();
    }

    public double getItemPrice() {
        return item.getItemPrice();
    }

    public double getItemVatRate() {
        return item.getVatRate();
    }
}
