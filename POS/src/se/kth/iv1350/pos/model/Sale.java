package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

import java.util.List;

/**
 * Tha class that is handling the sale. Contains methods and fields necessary for one sale instance.
 * @author Adrian Jonsson Sjoedin.
 */
public class Sale {
    private float runningTotalPrice;
    private float totalVatPrice;
    private float totalPrice;
    private List<ItemDTO> basket;

    /**
     * Creates an instance of sale.
     */
    public Sale() {
    }

}
