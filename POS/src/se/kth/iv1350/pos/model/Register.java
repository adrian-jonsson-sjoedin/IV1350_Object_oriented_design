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
        this.currentBalance = currentBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double registerPayment(double paidAmount, double totalPrice) {

    }

    /**
     * Updates the amount in the register.
     *
     * @param paidAmount the amount the customer paid.
     */
    private void updateBalance(double paidAmount) {

    }

    private double calculatedChange(double paidAmount, double totalPrice) {
        
        return totalPrice - paidAmount;
    }

}
