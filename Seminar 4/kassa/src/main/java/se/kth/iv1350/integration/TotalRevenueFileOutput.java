package se.kth.iv1350.integration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.kth.iv1350.model.TotalRevenueObserver;

public class TotalRevenueFileOutput implements TotalRevenueObserver{
    
    private float totalRevenue = 0;

    @Override
    public void addSale(float revenue){

        totalRevenue += revenue;

        try{
            writeFile();
        } catch(Exception e){
            System.out.println("[OBSERVER] Could not write file");
        }

    }

    private void writeFile() throws IOException{

        File file = new File("totalRevenue.md");
        file.delete();
        file.createNewFile();
        FileWriter newFile = new FileWriter("totalRevenue.md");
        newFile.write(String.format("Total revenue: %.2f SEK", totalRevenue));
        newFile.close();
        
    }

}