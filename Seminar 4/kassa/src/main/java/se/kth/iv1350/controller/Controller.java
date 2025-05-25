package se.kth.iv1350.controller;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.integration.AccountingDB;
import se.kth.iv1350.integration.DiscountDB;
import se.kth.iv1350.integration.InventoryDB;
import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Receipt;
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
     * @param itemID The item to be added
     * @param quantity The amount of the specified @link itemID items to add
     * @throws OperationErrorException
     */
    public void addItem(int itemID, int quantity) throws OperationErrorException{

        try {
            ItemDTO itemDTO = inventoryDB.getItemDTO(itemID);
            Item item = new Item(itemDTO, quantity);
            sale.addItem(item);
        } catch (Exception e) {
            System.out.println("[CONTROLLER] Could not add item: " + e.getMessage());
            throw new OperationErrorException("[CONTROLLER] Could not add item.", e);
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
     * Enters amount paid and prints the receipt
     * 
     * @param amount The amount paid
     * @throws OperationErrorException
     */
    public void enterPayment(float amount) throws OperationErrorException{

        try{
            sale.enterPayment(amount);
        } catch(Exception e){
            System.out.println("[CONTROLLER] Could not enter payment: " + e.getMessage());
            throw new OperationErrorException("[CONTROLLER] Could not enter payment.", e);
        }

        try {
            Receipt receipt = new Receipt(sale);
            receipt.print();
        } catch (Exception e) {
            System.out.println("[CONTROLLER] Could not print receipt: " + e.getMessage());
            throw new OperationErrorException("[CONTROLLER] Could not print receipt.", e);
        }

        try {
            inventoryDB.updateInventory(sale);
        } catch (Exception e) {
            System.out.println("[CONTROLLER] Could not update inventory: " + e.getMessage());
            throw new OperationErrorException("[CONTROLLER] Could not update inventory.", e);
        }

        sale = null;

    }

    /**
     * Terminates the sale and shows total price
     */
    public void endSale() {

        System.out.println(String.format("[CONTROLLER] Total: \t\t\t\t\t%.2f SEK.", sale.getTotalPrice()));
        System.out.println(String.format("[CONTROLLER] VAT: %.2f.", sale.getTotalVAT()));

    }

    /**
     * Requests discount for an customer with their specific ID
     * 
     * @param userID The customerID that is searched for
     * @throws OperationErrorException
     */
    public void requestDiscount(int userID) throws OperationErrorException{

        float discount = 0;

        try{
            discount = discountDB.checkTotalDiscount(userID, sale);
        } catch(Exception e){
            System.out.println("[CONTROLLER] Could not get discount: " + e.getMessage());
            throw new OperationErrorException("[CONTROLLER] Could not get discount.", e);
        }

        if(discount == 0) return;

        sale.setDiscount(discount);
        System.out.println(String.format("[CONTROLLER] \nCustomer %d is eligible for discount: -%.2f SEK.\n", userID, discount));

    }

}
