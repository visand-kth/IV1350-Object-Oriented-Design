package se.kth.iv1350.controller;

import se.kth.iv1350.integration.AccountingDB;
import se.kth.iv1350.integration.DiscountDB;
import se.kth.iv1350.integration.InventoryDB;
import se.kth.iv1350.integration.DatabaseFailureException;
import se.kth.iv1350.integration.ItemNotFoundException;
import se.kth.iv1350.model.Item;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.util.FileLogger;
import se.kth.iv1350.view.TotalRevenueLogging;
import se.kth.iv1350.view.TotalRevenueObserver;
import se.kth.iv1350.view.TotalRevenueView;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.integration.Printer;

/**
 * Controlling the interactions
 */
public class Controller {
    private AccountingDB accountingDB;
    private DiscountDB discountDB;
    private InventoryDB inventoryDB;
    private Sale sale;
    private Printer printer; 
    private List<TotalRevenueObserver> observers = new ArrayList<>();
    private FileLogger logger = new FileLogger();

    private void addObserver(TotalRevenueObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(double revenue) {
        for (TotalRevenueObserver obs : observers) {
            obs.newSale(revenue);
        }
    }

    /**
     * Constructor for @link Controller
     */
    public Controller() {
        TotalRevenueView revenueView = new TotalRevenueView();
        addObserver(revenueView);
        TotalRevenueLogging revenueLogging = new TotalRevenueLogging();
        addObserver(revenueLogging);

        System.out.println("Starting controller...");
        accountingDB = new AccountingDB();
        System.out.println("accountingDB: " + accountingDB);
        discountDB = new DiscountDB();
        System.out.println("discountDB: " + discountDB);
        inventoryDB = new InventoryDB();
        System.out.println("inventoryDB: " + inventoryDB);
        printer = new Printer();
        System.out.println("Printer: " + printer);

    }

    /**
     * Starts a new sale by creating a @link Sale
     */
    public void startSale() {
        sale = new Sale(observers);
    }

    /**
     * Adds item to the current @link Sale
     * 
     * @param item The item to be added
     */
    public void addItem(Item item) {
        if (item == null) {
            IllegalArgumentException e = new IllegalArgumentException("Cannot add null item to sale.");
            logger.logException(e);
            throw e;
        }
        System.out.println("Adding item: " + item.getItemDTO());
        if (item.getItemDTO() == null) {
            IllegalArgumentException e = new IllegalArgumentException("Cannot add item with null ItemDTO.");
            logger.logException(e);
            throw e;
        }
        try {
            sale.addItem(item);
        } catch (Exception e) {
            logger.logException(e);
            throw e;
        }
        
    }

    /**
     * Adds an item to the current sale by looking it up through its ID.
     * 
     * @param id The ID of the item to be added
     * @param amount The amount of the item to be added
     */
    public void addItemById(int id, int amount) {
        try{
            ItemDTO itemDTO = inventoryDB.findItem(id);
            Item item = new Item(itemDTO, amount);
            sale.addItem(item);
        } catch (ItemNotFoundException e) {
            logger.logException(e);
        } catch (DatabaseFailureException e) {
            logger.logException(e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            logger.logException(e);
        }
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
     * Terminates the sale, prints the receipt, updates accounting and inventory.
     */
    public void endSale(int personId) {
        try{
             Receipt receipt = sale.createReceipt();
            printer.print(receipt);
            if(personId != 0) {
                receipt.setDiscount(discountDB.getDiscount(personId));
            }
            accountingDB.storeReceipt(personId, receipt);
            for (Item item : sale.getItems()) {
                ItemDTO itemDTO = item.getItemDTO();
                inventoryDB.updateItem(itemDTO, item.getAmount());
            }
            notifyObservers(sale.getTotalPrice());
            sale = null;
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            System.out.println("Failed to end sale: " + e.getMessage());
        } catch (Exception e) {
            logger.logException(e);
            System.out.println("An unexpected error occurred while ending the sale: " + e.getMessage());
        }
    }
}
