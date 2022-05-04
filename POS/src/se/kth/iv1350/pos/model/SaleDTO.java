package se.kth.iv1350.pos.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Class for simplifying sale data transfer between layers.
 */
public class SaleDTO {
    private LocalTime timeOfSale = LocalTime.now();
    private LocalDate dateOfSale = LocalDate.now();
    private List<ItemInBasket> basket;
    private double runningTotalPrice;
    private double totalVATPrice;
    private double totalPrice;
    private double amountPaid;
    private double change;

    /**
     * Creates a new instance of the DTO.
     *
     * @param basket            An array of {@link ItemInBasket} for all the items of the sale
     * @param totalPrice        The total price of the sale, including VAT
     * @param totalVATPrice     The total amount of VAT
     * @param amountPaid        What the customer paid
     * @param change            What the customer received back
     * @param runningTotalPrice The total price of the items, excluding VAT.
     */
    public SaleDTO(List<ItemInBasket> basket, double runningTotalPrice,
                   double totalVATPrice, double totalPrice, double amountPaid, double change) {
        this.basket = basket;
        this.runningTotalPrice = runningTotalPrice;
        this.totalVATPrice = totalVATPrice;
        this.totalPrice = totalPrice;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    public LocalTime getTimeOfSale() {
        return timeOfSale;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public List<ItemInBasket> getBasket() {
        return basket;
    }

    public double getRunningTotalPrice() {
        return runningTotalPrice;
    }

    public double getTotalVATPrice() {
        return totalVATPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }
}

