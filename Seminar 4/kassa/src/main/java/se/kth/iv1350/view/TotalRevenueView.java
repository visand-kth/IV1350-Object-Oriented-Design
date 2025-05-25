package se.kth.iv1350.view;

import se.kth.iv1350.model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver{
    
    private float totalRevenue = 0;

    @Override
    public void addSale(float revenue){

        totalRevenue += revenue;
        System.out.println(String.format("[OBSERVER] Total revenue: %.2f SEK", totalRevenue));

    }

}
