package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.SaleDTO;

public class Printer {
    /**
     * This class represent the printer connected to the POS. It is not implemented as it is not needed for showing
     * the functionality of the POS.
     */
    public Printer() {
    }

    /**
     * Prints the receipt. Is not implemented, only tells that a receipt has been printed.
     * @param saleInfo
     */
    public void printReceipt(SaleDTO saleInfo) {
        System.out.println(">>> Printing receipt");
    }
}
