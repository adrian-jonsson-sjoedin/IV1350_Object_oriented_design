package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.*;

import java.util.List;

public class Controller {
    private ExternalInventorySystem exInventorySystem;
    private ExternalAccountingSystem exAccountingSystem;
    private MemberDatabase memberDB;
    private Sale sale;
    private Register register;
    private SaleDTO saleInfo;
    private Printer printer;

    /**
     * Creates an instance of the controller that is going to be used to access other layers. This is only done once.
     *
     * @param externalSystems Handles the integration of the external systems that controller need access to.
     * @param printer         The printer class that is going to be used to simulate printing a receipt.
     */
    public Controller(ExternalSystemsCreator externalSystems, Printer printer) {
        this.exInventorySystem = externalSystems.getExInventorySystem();
        this.exAccountingSystem = externalSystems.getExAccountingSystem();
        this.memberDB = externalSystems.getMemberDatabase();
        this.register = new Register(1000);
        this.printer = new Printer();
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
     * @return An object specifying if the item got added and the EAN code of said item.
     */
    public ScannedItemDTO scanAndAddNewItemFromInventoryToSale(int eanCode, int quantity) {
        if (exInventorySystem.checkIfItemExists(eanCode)) {
            sale.addItemToBasket(exInventorySystem.retrieveItem(eanCode), quantity);
            return new ScannedItemDTO(true, eanCode);
        }
        return new ScannedItemDTO(false, eanCode);
    }

    /**
     * This method displays all relevant information about the current sale to
     * the customer when called from view.
     */
    public List<ItemInBasket> getCurrentBasket() {
        return sale.getBasket();
    }

    /**
     * Gets the total price of the sale from <code>Sale</code>.
     *
     * @return The total price.
     */
    public double getTotal() {
        return sale.getTotalPrice();

    }

    /**
     * Gets the total running price of the sale from <code>Sale</code>.
     *
     * @return The total running price (without VAT added) of the current sale.
     */
    public double getRunningTotal() {
        return sale.getRunningTotalPrice();
    }

    /**
     * Gets the total running VAT price of the sale from <code>Sale</code>
     *
     * @return The total VAT price of the current sale.
     */
    public double getTotalVat() {
        return sale.getTotalVatPrice();
    }


    /**
     * Adds discount to purchase if customer is eligible.
     *
     * @param personalNr Identifier used  to check for eligibility.
     */
    public void addDiscount(long personalNr) {
        if (memberDB.customerIsMember(2001011111L)) {
            sale.addDiscount();
            System.out.println(">>> Discount applied");
        } else {
            System.out.println(">>> Customer is not a member");
        }
    }

    /**
     * Ends the sale. Calls the method to update the external accounting system and retrieves the sale information
     * from {@link Sale}.
     *
     * @param amountPaid Is the amount paid by the customer.
     */
    public void endSaleWithPayment(double amountPaid) {
        double change = register.registerPayment(amountPaid, sale.getTotalPrice());
        saleInfo = sale.endSale(amountPaid, change);
        updateExternalSystems(saleInfo);
    }

    private void updateExternalSystems(SaleDTO saleInfo) {
        exAccountingSystem.updateSaleInAccountingSystem(saleInfo);
    }

    /**
     * Getter to retrieve the change from SaleInfo
     *
     * @return the change for the customer
     */
    public double getChange() {
        return saleInfo.getChange();
    }

    /**
     * Retrieves the register balance.
     *
     * @return The amount currently stored in the register.
     */
    public double getRegisterBalance() {
        return register.getCurrentBalance();
    }

    /**
     * Passes on the request of creating a physical receipt.
     */
    public void getReceipt() {
        printer.printReceipt(this.saleInfo);
    }
}
