package se.kth.iv1350.view;

import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver{
    
    private float totalRevenue = 0;

    @Override
    public void addSale(Sale sale){

        totalRevenue += sale.getDiscountedPrice();
        System.out.println(String.format("Total revenue: %.2f SEK", totalRevenue));

    }

}
