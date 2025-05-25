package se.kth.iv1350.view;
import java.io.*;

/**
 * Observer that logs the total revenue to a file.
 */
public class TotalRevenueLogging implements TotalRevenueObserver {
    private static final String LOG_FILE = "total-revenue.txt";

    @Override
    public void newSale(double revenue) {
        writeTotalRevenue(revenue);
    }

    private void writeTotalRevenue(double totalRevenue) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, false))) {
            writer.println(totalRevenue);
        } catch (IOException e) {
            System.out.println("Could not write total revenue to file: " + e.getMessage());
        }
    }
}
