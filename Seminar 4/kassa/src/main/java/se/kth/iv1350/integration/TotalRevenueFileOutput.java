package se.kth.iv1350.integration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.kth.iv1350.model.TotalRevenueObserver;

/**
 * An observer that writes total revenue to a file: "totalRevenue.txt"
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {

    private float totalRevenue = 0;

    /**
     * Adds revenue to the observer to register
     * 
     * @param revenue Revenue to be added
     */
    @Override
    public void addSale(float revenue) {

        totalRevenue += revenue;

        try {
            writeFile();
        } catch (Exception e) {
            System.out.println("[OBSERVER] Could not write file");
        }

    }

    private void writeFile() throws IOException {

        File file = new File("totalRevenue.txt");
        file.delete();
        file.createNewFile();
        FileWriter newFile = new FileWriter("totalRevenue.txt");
        newFile.write(String.format("Total revenue: %.2f SEK", totalRevenue));
        newFile.close();

    }

}