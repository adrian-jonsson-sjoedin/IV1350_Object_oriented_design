package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that is handling the sale. Contains methods and fields necessary for one sale instance.
 *
 * @author Adrian Jonsson Sjoedin.
 */
public class Sale {
    private double runningTotalPrice;
    private double totalVatPrice;
    private double totalPrice;
    private List<ItemInBasket> basket = new ArrayList<>();
    private Discount discount;
    private boolean discountApplied = false;
    private double totalDiscount;
    private SaleDTO saleInfo;

    /**
     * Creates an instance of sale.
     */
    public Sale() {
        this.runningTotalPrice = 0;
        this.totalVatPrice = 0;
        this.totalPrice = 0;
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
            this.basket.add(new ItemInBasket(item, quantity));
        }
    }

    /**
     * Checks if an item is already added to the basket.
     *
     * @param item The item to check.
     * @return True if item is already in basket, otherwise false.
     */
    private boolean itemAlreadyScanned(ItemDTO item) {
        for (ItemInBasket itemInBasket : this.basket) {
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
        for (ItemInBasket itemInBasket : this.basket) {
            if (itemInBasket.getItemEanCode() == item.getEanCode()) {
                itemInBasket.setQuantity(itemInBasket.getQuantity() + quantity);
            }
        }
    }

    /**
     * This method applies a discount to sale
     */
    public void addDiscount() {
        this.discountApplied = true;
        this.discount = new Discount(this.basket);
        this.totalDiscount = discount.buyThreeOrMoreGetOneFree() +
                discount.buyForMoreThanOneHundred(this.runningTotalPrice);

    }

    /**
     * This method will update the field runningTotalPrice which is the price of all items in the basket without the
     * VAT added
     */
    private void updateRunningTotalPrice() {
        this.runningTotalPrice = 0;
        for (ItemInBasket itemInBasket : this.basket) {
            this.runningTotalPrice += itemInBasket.getQuantity() * itemInBasket.getItemPrice();
        }
    }

    /**
     * This method will update the total VAT that will be added to the sale
     */
    private void updateTotalVat() {
        this.totalVatPrice = 0;
        for (ItemInBasket itemInBasket : this.basket) {
            this.totalVatPrice += itemInBasket.getQuantity() *
                    (itemInBasket.getItemPrice() * (itemInBasket.getItemVatRate() / 100));
        }
    }

    /**
     * This method calculates the total price of the sale
     */
    private void calculateTotalPrice() {
        updateRunningTotalPrice();
        updateTotalVat();
        if (this.discountApplied) {
            this.totalPrice = this.runningTotalPrice + this.totalVatPrice - this.totalDiscount;
        } else {
            this.totalPrice = this.runningTotalPrice + this.totalVatPrice;
        }
    }

    public double getRunningTotalPrice() {
        updateRunningTotalPrice();
        return runningTotalPrice;
    }

    public double getTotalVatPrice() {
        updateTotalVat();
        return totalVatPrice;
    }

    public double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    public List<ItemInBasket> getBasket() {
        return basket;
    }

    /**
     *
     * @param amountPaid What the customer paid
     * @param change The change tey received
     * @return
     */
    public SaleDTO endSale(double amountPaid, double change) {
        saleInfo = new SaleDTO(this.basket, runningTotalPrice, totalVatPrice, totalPrice, amountPaid, change);
        return saleInfo;
    }
}
