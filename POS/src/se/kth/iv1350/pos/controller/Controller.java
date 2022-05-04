package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.ItemInBasket;
import se.kth.iv1350.pos.model.Register;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleDTO;

import java.util.List;

public class Controller {
    private ExternalInventorySystem exInventorySystem;
    private ExternalAccountingSystem exAccountingSystem;
    private MemberDatabase memberDB;
    private Sale sale;
    private Register register;
    private SaleDTO saleInfo;

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
        } else {
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
            printToString(itemInBasket);
        }
        System.out.println("------------------------------------------------------");
        System.out.printf("Price: %-1.2f:-%n", sale.getRunningTotalPrice());
        System.out.printf("VAT: %-1.2f:-%n", sale.getTotalVatPrice());
    }

    public void displayTotal() {
        System.out.printf("Total: %-1.2f:-%n", sale.getTotalPrice());
    }

    private void printToString(ItemInBasket item) {
        String itemName = item.getItemName();
        double itemPrice = item.getItemPrice();
        int itemQuantity = item.getQuantity();
        System.out.printf("%-20s %1d * %1.2f:- %n ", itemName, itemQuantity, itemPrice);
        System.out.println();
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

    public void initializePaymentAndEndSale(double amountPaid) {
        double change = register.registerPayment(amountPaid, sale.getTotalPrice());
        saleInfo = sale.endSale(amountPaid, change);
        updateExternalSystems(saleInfo);
    }

    private void updateExternalSystems(SaleDTO saleInfo) {
        exAccountingSystem.updateSaleInAccountingSystem(saleInfo);
    }

    /**
     * Getter to retrieve the change from SaleInfo
     * @return the change for the customer
     */
    public double getChange() {
        return saleInfo.getChange();
    }

    public double getRegisterBalance() {
        return  register.getCurrentBalance();
    }
}
