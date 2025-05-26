package se.kth.iv1350.controller;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.integration.AccountingDB;
import se.kth.iv1350.integration.DiscountDB;
import se.kth.iv1350.integration.InvalidCustomerIDException;
import se.kth.iv1350.integration.InvalidItemIDException;
import se.kth.iv1350.integration.InventoryDB;
import se.kth.iv1350.integration.NoConnectionException;
import se.kth.iv1350.integration.Printer;
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

        accountingDB = new AccountingDB();
        discountDB = new DiscountDB();
        inventoryDB = new InventoryDB();

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
     * @param itemID   The item to be added
     * @param quantity The amount of the specified @link itemID items to add
     * @throws InvalidItemIDException
     * @throws NoConnectionException
     */
    public ItemDTO addItem(int itemID, int quantity) throws InvalidItemIDException, NoConnectionException {

        ItemDTO itemDTO = inventoryDB.getItemDTO(itemID);
        Item item = new Item(itemDTO, quantity);
        sale.addItem(item);
        return itemDTO;

    }

    /**
     * Enters amount paid and prints the receipt
     * 
     * @param amount The amount paid
     */
    public void enterPayment(float amount) {

        sale.enterPayment(amount);
        Printer printer = new Printer();
        printer.print(sale.getSaleDTO(), amount);
        inventoryDB.updateInventory(sale.getSaleDTO());
        sale = null;

    }

    /**
     * Terminates the sale and shows total price
     */
    public SaleDTO endSale() {

        return sale.getSaleDTO();

    }

    /**
     * Getter for sale.getSaleDTO()
     * 
     * @return Returns the value of the variable saleDTO in sale
     */
    public SaleDTO getSaleDTO() {

        if (sale == null)
            return null;

        return sale.getSaleDTO();

    }

    /**
     * Getter for the sale variable (purely for testing purposes)
     * 
     * @return Returns the value of the current sale
     */
    public Sale getSale() {

        return sale;

    }

    /**
     * Requests discount for an customer with their specific ID
     * 
     * @param userID The customerID that is searched for
     * @throws InvalidCustomerIDException
     * @throws NoConnectionException
     */
    public void requestDiscount(int userID) throws InvalidCustomerIDException, NoConnectionException {

        sale.setDiscount(discountDB.checkTotalDiscount(userID, sale.getSaleDTO()));

    }

}
