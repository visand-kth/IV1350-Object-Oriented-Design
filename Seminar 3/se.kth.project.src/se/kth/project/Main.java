package se.kth.project;

import se.kth.project.Controller.Controller;
import se.kth.project.External.AccountingDB;
import se.kth.project.External.DiscountDB;
import se.kth.project.External.InventoryDB;

public class Main {
    public static void main(String[] args) {
        // Initialize external systems
        AccountingDB accountingDB = new AccountingDB();
        DiscountDB discountDB = new DiscountDB();
        InventoryDB inventoryDB = new InventoryDB();

        // Initialize controller
        Controller controller = new Controller(accountingDB, inventoryDB);

        // Simulate a sale
        controller.startSale();
        System.out.println("Sale started.");

        // Add items to the sale (example item IDs and quantities)
        controller.startSale(); 
        //add items to the sale
        if (controller.currentSale.addItem(1, 2)) {
            System.out.println("Item added to the sale.");
        } else {
            System.out.println("Failed to add item to the sale.");
        }

        // End the sale
        controller.endSale();
        System.out.println("Sale ended.");
    }
}
