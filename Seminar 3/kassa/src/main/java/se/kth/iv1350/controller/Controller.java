package se.kth.iv1350.controller;

import se.kth.iv1350.integration.AccountingDB;
import se.kth.iv1350.integration.DiscountDB;
import se.kth.iv1350.integration.InventoryDB;
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

    public void startSale() {

        sale = new Sale();

    }

}
