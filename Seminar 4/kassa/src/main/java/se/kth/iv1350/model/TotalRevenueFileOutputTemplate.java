package se.kth.iv1350.model;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class TotalRevenueFileOutputTemplate extends TotalRevenueObserverTemplate{
    
    private float totalRevenue = 0;

    @Override
    protected void calculateTotalIncome(float addSalePrice){

        totalRevenue += addSalePrice;
        writeFile();

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
            System.out.println("[OBSERVER (TEMPLATE)] " + e.getMessage());
        }
        
    }

    @Override
    protected void doShowTotalIncome() throws ObserverTotalIncomeDisplayException{

        if(totalRevenue <= 0)
            throw new ObserverTotalIncomeDisplayException("No or negative revenue: " + totalRevenue);

        File file = new File("totalRevenue.md");
        Scanner scanner = null;

        try{
            scanner = new Scanner(file);
        } catch (Exception e){
            throw new ObserverTotalIncomeDisplayException("File not found");
        }
        
        if(!scanner.hasNextLine())
            throw new ObserverTotalIncomeDisplayException("totalRevenue file empty");

        String fileContent = scanner.nextLine();
        scanner.close();

        System.out.println("[OBSERVER (TEMPLATE)] " + fileContent);

    }

    @Override
    protected void handleErrors(Exception e){

        System.out.println("[OBSERVER (TEMPLATE)] TotalRevenueView was not able to register revenue: " + e.getMessage());

    }

}
