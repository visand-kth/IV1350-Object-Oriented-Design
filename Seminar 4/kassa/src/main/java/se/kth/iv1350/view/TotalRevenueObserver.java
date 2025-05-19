package se.kth.iv1350.view;

/**
 * Observer interface for receiving updates about total revenue.
 */
public interface TotalRevenueObserver {
    /**
     * Called when a new sale has been completed and revenue should be updated.
     * @param revenue The updated total revenue.
     */
    void newSale(double revenue);
}