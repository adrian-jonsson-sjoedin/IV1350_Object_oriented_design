package se.kth.iv1350.pos.model;

/**
 * The register for the POS that updates
 */
public class Register {
    private double currentBalance;

    /**
     * Initializes the register. Should only be done once.
     *
     * @param startingBalance The amount in the register at the start
     */
    public Register(double startingBalance) {
        this.currentBalance = startingBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Registers the payment for the customer.
     *
     * @param paidAmount The amount paid.
     * @param totalPrice The total cost of the sale.
     * @return The change the customer receives.
     */
    public double registerPayment(double paidAmount, double totalPrice) {
        double change = calculatedChange(paidAmount, totalPrice);
        if (change == -1) {
            System.out.println("Amount paid is to little");
        } else {
            this.currentBalance -= change;
            this.currentBalance += paidAmount;
        }
        return  change;
    }

    /**
     * Calculate how much change the customer should receive.
     *
     * @param paidAmount The amount paid
     * @param totalPrice The total cost of the sale
     * @return Returns the change. If customer paid to little returns -1.
     */
    private double calculatedChange(double paidAmount, double totalPrice) {
        double change = paidAmount - totalPrice;
        if (change >= 0)
            return paidAmount - totalPrice;
        return -1;
    }

}
