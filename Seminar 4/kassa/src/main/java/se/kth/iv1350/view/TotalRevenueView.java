package se.kth.iv1350.view;

import se.kth.iv1350.view.TotalRevenueObserver;
import se.kth.iv1350.view.TotalRevenueLogging;

/**
 * Observer that displays the total revenue in the console.
 */
public class TotalRevenueView implements TotalRevenueObserver {
    public void newSale(double revenue) {
        System.out.println("Total revenue: " + revenue);
    }
}
