package se.kth.iv1350.integration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.kth.iv1350.model.TotalRevenueObserverTemplate;

/**
 * An observer that writes total revenue to a file: "totalRevenue.txt"
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class TotalRevenueFileOutputTemplate extends TotalRevenueObserverTemplate {

    private float totalRevenue = 0;

    @Override
    protected void calculateTotalIncome(float addSalePrice) {

        totalRevenue += addSalePrice;

    }

    private void writeFile() throws IOException {

        File file = new File("totalRevenueTemplate.txt");
        file.delete();
        file.createNewFile();
        FileWriter newFile = new FileWriter("totalRevenueTemplate.txt");
        newFile.write(String.format("Total revenue: %.2f SEK", totalRevenue));
        newFile.close();

    }

    @Override
    protected void doShowTotalIncome() throws Exception {

        writeFile();

    }

    @Override
    protected void handleErrors(Exception e) {

        System.out.println("[OBSERVER (TEMPLATE)] TotalRevenueView was not able to register revenue to file");

    }

}
