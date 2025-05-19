package se.kth.iv1350.controller;

import se.kth.iv1350.integration.AccountingDB;
import se.kth.iv1350.integration.DiscountDB;
import se.kth.iv1350.integration.InventoryDB;
import se.kth.iv1350.integration.DatabaseFailureException;
import se.kth.iv1350.integration.ItemNotFoundException;
import se.kth.iv1350.model.Item;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.model.Sale;
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
        printer = new Printer();
        System.out.println("Printer: " + printer);

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
        try {
            if (item == null) {
                throw new IllegalArgumentException("Cannot add null item to sale.");
            }
            if (item.getItemDTO() == null) {
                throw new IllegalArgumentException("Cannot add item with null ItemDTO.");
            }
            sale.addItem(item);
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    /**
     * Adds an item to the current sale by looking it up through its ID.
     * 
     * @param id The ID of the item to be added
     * @param amount The amount of the item to be added
     */
    public void addItemById(int id, int amount) throws ItemNotFoundException, DatabaseFailureException {
        try {
            ItemDTO itemDTO = inventoryDB.findItem(id);
            Item item = new Item(itemDTO, amount);
            sale.addItem(item);
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (DatabaseFailureException e) {
            // Notify view/user and log
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

        sale = null;
    }

}
