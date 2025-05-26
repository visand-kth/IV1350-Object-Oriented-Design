package se.kth.iv1350.integration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import se.kth.iv1350.model.ObserverTotalIncomeDisplayException;
import se.kth.iv1350.model.TotalRevenueObserverTemplate;

public class TotalRevenueFileOutputTemplate extends TotalRevenueObserverTemplate{
    
    private float totalRevenue = 0;

    @Override
    protected void calculateTotalIncome(float addSalePrice){

        totalRevenue += addSalePrice;
        
        try{
            writeFile();
        }catch(Exception e){
            System.out.println("[OBSERVER (TEMPLATE)] Could not write file");
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

    @Override
    protected void doShowTotalIncome() throws ObserverTotalIncomeDisplayException{

        File file = new File("totalRevenue.md");
        Scanner scanner = null;

        try{
            scanner = new Scanner(file);
        } catch (Exception e){
            throw new ObserverTotalIncomeDisplayException("File not found");
        }
        
        if(!scanner.hasNextLine())
            System.out.println("[OBSERVER (TEMPLATE)] No revenue in file");

        String fileContent = scanner.nextLine();
        scanner.close();

        System.out.println("[OBSERVER (TEMPLATE)] " + fileContent);

    }

    @Override
    protected void handleErrors(Exception e){

        System.out.println("[OBSERVER (TEMPLATE)] TotalRevenueView was not able to register revenue: " + e.getStackTrace());

    }

}
