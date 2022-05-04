package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.SaleDTO;

/**
 * This class represent the accounting system. It is not implemented as it is not needed for showing
 * the functionality of the POS as accounting is not relevant for the user.
 */
public class ExternalAccountingSystem {
    /**
     * Initializes the accounting system. Should only be called once.
     */
    public ExternalAccountingSystem() {
    }

    /**
     * Updates the accounting system. Is not implemented, only tells that there has been an update to the system.
     *
     * @param saleInfo The information that should bo stored in the accounting system.
     */
    public void updateSaleInAccountingSystem(SaleDTO saleInfo) {
        System.out.println(">>> Accounting system has been updated");

    }
}
