package se.kth.iv1350.pos.model;

/**
 * Class that implements the Observer pattern for keeping track of updates to total revenue
 */
public interface SaleObserver {
    /**
     * Method for updating the observer
     * @param total The total income of the sale
     */
    void totalRevenue(double total);
}
