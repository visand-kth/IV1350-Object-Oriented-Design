package se.kth.iv1350.view;
import se.kth.iv1350.view.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver {
    public void newSale(double revenue) {
        System.out.println("Total revenue: " + revenue);
    }
}
