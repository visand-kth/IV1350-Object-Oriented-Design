package se.kth.iv1350.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles an sale with @link Item and @link payment
 * Creates an @link receipt
 */
public class Sale {

    private List<Item> items;
    private float totalPrice;
    private float totalVAT;

    /**
     * Constructor for @link Sale
     */
    public Sale() {

        items = new ArrayList<>();

    }

    /**
     * Adds item to the items list
     * 
     * @param item The item to be added
     */
    public void addItem(Item item) {
        System.out.println();

        int potentialDuplicate = checkDuplicate(item);

        if (potentialDuplicate < 0)
            items.add(item);
        else
            addToExisting(potentialDuplicate, item);
        
        calculateTotal();

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
    public int checkDuplicate(Item newItem) {

        for (int i = 0; i < items.size(); i++) {

            Item saleItem = items.get(i);
            int saleItemID = saleItem.getItemDTO().getID();
            int newItemID = newItem.getItemDTO().getID();

            if (saleItemID == newItemID)
                return i;

        }

        return -1;

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
     * @return Returns the @link Item that exists in the current sale, returns null
     *         if item does not exist
     */
    public Item findItem(int ID) {

        for (Item item : items) {

            if (item.getItemDTO().getID() == ID)
                return item;

        }

        return null;

    }

    public List<Item> getItems() { return items; }
    public float getTotalVAT() { return totalVAT; }

    /**
     * Creates a receipt for this sale.
     * @return A new Receipt object representing this sale.
     */
    public Receipt createReceipt() {
        return new Receipt(this);
    }
}
