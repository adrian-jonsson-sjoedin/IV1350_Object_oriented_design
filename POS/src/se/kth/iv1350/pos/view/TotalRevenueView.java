package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.SaleObserver;

/**
 * Class that represent the total revenue since the POS started and that should be displayed in view.
 */
public class TotalRevenueView implements SaleObserver {
    private double totalRevenue;

    /**
     * Creates a new instance of TotalRevenue. Should only be called once when the program starts.
     */
    public TotalRevenueView() {
        this.totalRevenue = 0d;
    }

    /**
     * Displays the total revenue generated since the start of the POS.
     *
     * @param total The total income of the sale
     */
    @Override
    public void totalRevenue(double total) {
        this.totalRevenue += total;
        output(this.totalRevenue);
    }

    private void output(double amount) {
        System.out.println("------------------------------------------------------");
        System.out.println(" Total revenue since the POS started");
        System.out.printf("Price: %-1.2f:-%n", amount);
        System.out.println("------------------------------------------------------");
    }
}
