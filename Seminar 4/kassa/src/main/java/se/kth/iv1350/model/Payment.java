package se.kth.iv1350.model;

/**
 * Processes payment for a sale.
 */
public class Payment {
    private final float amountPaid;

    /**
     * Creates a new Payment.
     * @param amountPaid The amount paid by the customer.
     */
    public Payment(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return The amount paid by the customer.
     */
    public float getAmountPaid() {
        return amountPaid;
    }
}
