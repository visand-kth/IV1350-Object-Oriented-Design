package se.kth.iv1350.model;

import java.io.File;
import java.io.FileWriter;

public class TotalRevenueFileOutput implements TotalRevenueObserver{
    
    private float totalRevenue = 0;

    @Override
    public void addSale(float revenue){

        totalRevenue += revenue;
        System.out.println(String.format("Total revenue: %.2f SEK", totalRevenue));

    }

    private void writeFile(){

        try{

        File file = new File("totalRevenue.md");
        file.delete();
        file.createNewFile();
        FileWriter newFile = new FileWriter("totalRevenue.md");
        newFile.write(String.format("Total revenue: %.2f SEK", totalRevenue));
        newFile.close();

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

}