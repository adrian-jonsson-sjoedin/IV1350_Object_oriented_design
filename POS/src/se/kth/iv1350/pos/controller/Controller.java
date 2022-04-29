package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.ExternalSystemsCreator;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.model.Sale;

public class Controller {

    /**
     * Creates an instance of the controller that is going to be used to access other layers. This is only done once.
     *
     * @param externalSystems Handles the integration of the external systems that controller need access to.
     * @param printer         The printer class that is going to be used to simulate printing a receipt.
     */
    public Controller(ExternalSystemsCreator externalSystems, Printer printer) {

    }

    /**
     * Starts a new sale and creates an instance of Sale. This method must be called first before doing anything else
     * during a sale.
     */
    public void initializeNewSale() {
        Sale sale = new Sale();
    }

    /**
     * Takes the item's EAN code from view.
     * @param eanCode The item's unique identifier.
     */
    public void scanItem(int eanCode) {

    }
}
