package se.kth.iv1350.pos.model;

import java.util.List;

/**
 * This class handles all discount requests.
 */
public class Discount {
    private List<ItemInBasket> basket;

    /**
     * Creates an instance of discount.
     *
     * @param basket The basket containing the items of the customer.
     */
    public Discount(List<ItemInBasket> basket) {
        this.basket = basket;
    }

    /**
     * If there's three or more of the same item in the basket a discount is applied.
     *
     * @return Returns the total amount that should be subtracted.
     */
    public double buyThreeOrMoreGetOneFree() {
        double amountToSubtract = 0;
        for (ItemInBasket itemInBasket : this.basket) {
            if (itemInBasket.getQuantity() >= 3) {
                amountToSubtract += itemInBasket.getItemPrice();
            }
        }
        return amountToSubtract;
    }

    /**
     * If the total price (not including VAT) of the items in basket is more than 100 a discount is applied.
     *
     * @param totalRunningPrice The total price of the items in the basket.
     * @return Returns the total amount that should be subtracted.
     */
    public double buyForMoreThanOneHundred(double totalRunningPrice) {
        if (totalRunningPrice > 100)
            return totalRunningPrice * 0.10;
        return 0;
    }


}
