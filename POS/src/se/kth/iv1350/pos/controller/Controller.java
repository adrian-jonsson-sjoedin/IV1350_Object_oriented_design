package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.ItemInBasket;
import se.kth.iv1350.pos.model.Sale;

import java.util.List;

public class Controller {
    private ExternalInventorySystem exInventorySystem;
    private ExternalAccountingSystem exAccountingSystem;
    private MemberDatabase membertDB;
    private Sale sale;

    /**
     * Creates an instance of the controller that is going to be used to access other layers. This is only done once.
     *
     * @param externalSystems Handles the integration of the external systems that controller need access to.
     * @param printer         The printer class that is going to be used to simulate printing a receipt.
     */
    public Controller(ExternalSystemsCreator externalSystems, Printer printer) {
        this.exInventorySystem = externalSystems.getExInventorySystem();
        this.exAccountingSystem = externalSystems.getExAccountingSystem();
        this.membertDB = externalSystems.getMemberDB();
    }

    /**
     * Starts a new sale and creates an instance of Sale. This method must be called first before doing anything else
     * during a sale.
     */
    public void initializeNewSale() {
        this.sale = new Sale();
    }

    /**
     * Uses the item's identifier to find and retrieve the item from the external inventory system and add it
     * to the sale.
     *
     * @param eanCode  The item's unique identifier.
     * @param quantity How many of this item that should be added.
     */
    public void scanAndAddNewItemFromInventoryToSale(int eanCode, int quantity) {
        if (exInventorySystem.checkIfItemExists(eanCode)) {
            sale.addItemToBasket(exInventorySystem.retrieveItem(eanCode), quantity);
        }else{
            System.out.println();
            System.out.println("Invalid EAN code: " + eanCode);
        }
    }

    /**
     * This method displays all relevant information about the current sale to
     * the customer when called from view.
     */
    public void displayCurrentSaleInfo() {
        List<ItemInBasket> currentBasket = sale.getBasket();
        for (ItemInBasket itemInBasket : currentBasket) {
            printString(itemInBasket);
        }
        System.out.println("------------------------------------------------------");
        System.out.printf("Price: %-1.2f:-%n", sale.getRunningTotalPrice());
        System.out.printf("VAT: %-1.2f:-%n", sale.getTotalVatPrice());
       // System.out.printf("Total: %-1.2f:-%n", sale.getTotalPrice());
    }


    private void printString(ItemInBasket item) {
        String itemName = item.getItemName();
        double itemPrice = item.getItemPrice();
        int itemQuantity = item.getQuantity();
        System.out.printf("%-20s %1d * %1.2f:- %n ", itemName, itemQuantity, itemPrice);
        System.out.println();
    }

}
