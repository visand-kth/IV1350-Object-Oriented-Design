package se.kth.iv1350.view;

import se.kth.iv1350.model.TotalRevenueObserver;

/**
 * View observer that prints the total revenue to the view
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class TotalRevenueView implements TotalRevenueObserver {

    private float totalRevenue = 0;

    /**
     * Adds revenue to the observer to register
     * 
     * @param revenue Revenue to be added
     */
    @Override
    public void addSale(float revenue) {

        totalRevenue += revenue;
        System.out.println(String.format("[OBSERVER] Total revenue: %.2f SEK", totalRevenue));

    }

}
