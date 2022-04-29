package se.kth.iv1350.pos.integration;

/**
 * This class is responsible for retrieving all the external systems
 */
public class ExternalSystemsCreator {
    private ExternalInventorySystem exInventorySystem = new ExternalInventorySystem();
    private ExternalAccountingSystem exAccountingSystem = new ExternalAccountingSystem();
    private DiscountDatabase discounts = new DiscountDatabase();

    /**
     * Retrieves ExternalInventorySystem
     * @return The value of exInventorySystem
     */
    public ExternalInventorySystem getExInventorySystem() {
        return exInventorySystem;
    }

    /**
     * Retrieves ExternalAccountingSystem
     * @return The value of exAccountingSystem
     */
    public ExternalAccountingSystem getExAccountingSystem() {
        return exAccountingSystem;
    }

    /**
     * Retrieves DiscountDatabase
     * @return The value of discounts
     */
    public DiscountDatabase getDiscounts() {
        return discounts;
    }
}
