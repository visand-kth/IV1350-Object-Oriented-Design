package se.kth.iv1350.model;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.integration.ItemNotFoundException;
import se.kth.iv1350.view.TotalRevenueObserver;

/**
 * Handles an sale with @link Item and @link payment
 * Creates an @link receipt
 */
public class Sale {

    private List<Item> items;
    private float totalPrice;
    private float totalVAT;
    private List<TotalRevenueObserver> observers;

    /**
     * Constructor for @link Sale
     */
    public Sale(List<TotalRevenueObserver> observers) {

        items = new ArrayList<>();
        this.observers = observers;
    }

    /**
     * Adds item to the items list
     * 
     * @param item The item to be added
     * @throws IllegalArgumentException if the item is null or has invalid data
     */
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to sale.");
        }
        if (item.getItemDTO() == null) {
            throw new IllegalArgumentException("Cannot add item with null ItemDTO.");
        }
        try{
            int potentialDuplicate = checkDuplicate(item);
            addToExisting(potentialDuplicate, item);
        } catch (ItemNotFoundException e) {
            // If no duplicate is found, add the item as a new entry
            items.add(item);
        }
        calculateTotal();
        notifyObservers(totalPrice);
    }

    void addToExisting(int index, Item item) {

        Item existingItem = items.get(index);
        int currentAmount = existingItem.getAmount();
        int newAmount = currentAmount + item.getAmount();
        Item newItem = new Item(existingItem.getItemDTO(), newAmount);
        items.set(index, newItem);

    }

    /**
     * Sums the price of each @link Item in the current @link Sale
     */
    public void calculateTotal() {

        totalPrice = 0;
        totalVAT = 0;

        for (Item item : items) {

            float itemPrice = item.getItemDTO().getTotalPrice();
            totalPrice += itemPrice;
            totalVAT += itemPrice * item.getItemDTO().getVAT();

        }

    }

    /**
     * Checks for duplicate item in the current sale
     * 
     * @param item The item to compare
     * @return The index of a potential duplicate (-1 in case of no duplicate)
     */
    public int checkDuplicate(Item newItem) throws ItemNotFoundException {

        for (int i = 0; i < items.size(); i++) {

            Item saleItem = items.get(i);
            int saleItemID = saleItem.getItemDTO().getID();
            int newItemID = newItem.getItemDTO().getID();

            if (saleItemID == newItemID)
                return i;

        }
        throw new ItemNotFoundException(newItem.getItemDTO().getID());  
    }

    /**
     * Getter for the variable totalPrice
     * 
     * @return Returns the value of totalPrice
     */
    public float getTotalPrice() {

        return totalPrice;

    }

    /**
     * Searches for the itemID in the current sale
     * 
     * @param ID The ID to search for in this particular sale
     * @return Returns the @link Item that exists in the current sale
     * @throws ItemNotFoundException if item does not exist
     */
    public Item findItem(int ID) throws ItemNotFoundException {
        for (Item item : items) {
            if (item.getItemDTO().getID() == ID)
                return item;
        }
        throw new ItemNotFoundException(ID);
    }

    /**
     * Getter for the variable items
     * @return Returns the items in a list of type Item
     */
    public List<Item> getItems() {
        return items;
    }
    
    /**
     * Getter for the variable totalVAT
     * 
     * @return Returns the value of totalVAT
     */
    public float getTotalVAT() {
        return totalVAT;
    }

    /**
     * Creates a receipt for this sale.
     * @return A new Receipt object representing this sale.
     */
    public Receipt createReceipt() {
        return new Receipt(this);
    }


    private void notifyObservers(double totalRevenue) {
        for (TotalRevenueObserver obs : observers) {
            obs.newSale(totalRevenue);
        }
    }
}
