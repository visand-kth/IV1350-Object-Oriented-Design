package se.kth.iv1350.view;

import se.kth.iv1350.model.ObserverTotalIncomeDisplayException;
import se.kth.iv1350.model.TotalRevenueObserverTemplate;

public class TotalRevenueViewTemplate extends TotalRevenueObserverTemplate{
    
    private float totalRevenue = 0;

    @Override
    protected void calculateTotalIncome(float addSalePrice){

        totalRevenue += addSalePrice;

    }

    @Override
    protected void doShowTotalIncome() throws ObserverTotalIncomeDisplayException{

        if(totalRevenue <= 0)
            throw new ObserverTotalIncomeDisplayException("No or negative revenue: " + totalRevenue);

        System.out.println(String.format("[OBSERVER (TEMPLATE)] Total revenue: %.2f SEK", totalRevenue));

    }

    @Override
    protected void handleErrors(Exception e){

        System.out.println("[OBSERVER (TEMPLATE)] TotalRevenueView was not able to register revenue: " + e.getStackTrace());

    }

}
