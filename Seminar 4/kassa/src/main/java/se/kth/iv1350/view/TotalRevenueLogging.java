package se.kth.iv1350.view;
import java.io.*;

/**
 * Observer that logs the total revenue to a file.
 */
public class TotalRevenueLogging implements TotalRevenueObserver {
    private static final String LOG_FILE = "total-revenue.txt";

    @Override
    public void newSale(double revenue) {
        double totalRevenue = readTotalRevenue();
        totalRevenue += revenue;
        writeTotalRevenue(totalRevenue);
    }

    private double readTotalRevenue() {
        double total = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                total = Double.parseDouble(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            // If file does not exist or is invalid, start from 0
        }
        return total;
    }

    private void writeTotalRevenue(double totalRevenue) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, false))) {
            writer.println(totalRevenue);
        } catch (IOException e) {
            System.out.println("Could not write total revenue to file: " + e.getMessage());
        }
    }
}
