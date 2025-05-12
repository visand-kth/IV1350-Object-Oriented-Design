package se.kth.iv1350.controller;

import se.kth.iv1350.integration.AccountingDB;
import se.kth.iv1350.integration.DiscountDB;
import se.kth.iv1350.integration.InventoryDB;
import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Sale;

/**
 * Controlling the interactions
 */
public class Controller {

    private AccountingDB accountingDB;
    private DiscountDB discountDB;
    private InventoryDB inventoryDB;
    private Sale sale;

    /**
     * Constructor for @link Controller
     */
    public Controller() {

        System.out.println("Starting controller...");
        accountingDB = new AccountingDB();
        System.out.println("accountingDB: " + accountingDB);
        discountDB = new DiscountDB();
        System.out.println("discountDB: " + discountDB);
        inventoryDB = new InventoryDB();
        System.out.println("inventoryDB: " + inventoryDB);

    }

    /**
     * Starts a new sale by creating a @link Sale
     */
    public void startSale() {

        sale = new Sale();

    }

    /**
     * Adds item to the current @link Sale
     * 
     * @param item The item to be added
     */
    public void addItem(Item item) {

        sale.addItem(item);

    }

    /**
     * Getter for the variable sale
     * 
     * @return Returns the value of sale
     */
    public Sale getSale() {

        return sale;

    }

    /**
     * Terminates the sale and prints the receipt
     */
    public void endSale(){

        System.out.println("End sale:");
        sale = null;

    }

}
