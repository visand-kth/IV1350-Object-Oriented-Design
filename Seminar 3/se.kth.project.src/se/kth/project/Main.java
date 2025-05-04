package se.kth.project;

import se.kth.project.Controller.Controller;
import se.kth.project.External.AccountingDB;
import se.kth.project.External.DiscountDB;
import se.kth.project.External.InventoryDB;
import se.kth.project.Model.Item;

public class Main {
    public static void main(String[] args) {
        // Initialize external systems
        AccountingDB accountingDB = new AccountingDB();
        DiscountDB discountDB = new DiscountDB();
        InventoryDB inventoryDB = new InventoryDB();

        // Add items to the inventory
        inventoryDB.addItem(new Item(29.90f, 1, "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free", 0.06f), 10);
        inventoryDB.addItem(new Item(14.90f, 2, "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour", 0.06f), 30);
        inventoryDB.addItem(new Item(19.90f, 3, "ChocoBar Deluxe 100g, premium dark chocolate", 0.12f), 20);
        inventoryDB.addItem(new Item(9.90f, 4, "FreshApple Juice 1L, 100% natural apple juice", 0.12f), 10);
        inventoryDB.addItem(new Item(49.90f, 5, "Organic Almond Butter 250g, no added sugar", 0.06f), 5);
        inventoryDB.addItem(new Item(5.90f, 6, "Classic White Bread 500g, freshly baked", 0.12f), 2);
        inventoryDB.addItem(new Item(39.90f, 7, "Premium Coffee Beans 500g, dark roast", 0.25f), 1);
        inventoryDB.addItem(new Item(24.90f, 8, "Cheddar Cheese 200g, aged and creamy", 0.12f), 300);

        accountingDB.addCustomer(12345);
        discountDB.addCustomer(12345, 0.10f); // 10% discount for customer with ID 12345

        // Initialize controller
        Controller controller = new Controller(accountingDB,discountDB, inventoryDB);

        // Simulate a sale
        controller.startSale();
        System.out.println("Sale started.");

        // Add items to the sale (example item IDs and quantities)
        if (controller.currentSale.addItem(1, 2)) {
            System.out.println("Item added to the sale.");
        } else {
            System.out.println("Failed to add item to the sale.");
        }

        if (controller.currentSale.addItem(3, 1)) {
            System.out.println("Item added to the sale.");
        } else {
            System.out.println("Failed to add item to the sale.");
        }

        if (controller.currentSale.addItem(5, 1)) {
            System.out.println("Item added to the sale.");
        } else {
            System.out.println("Failed to add item to the sale.");
        }

        // End the sale
        controller.endSale();
        System.out.println("Sale ended.");
    }
}
