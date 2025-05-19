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
    private float amountPaid;
    private float priceReduction;

    /**
     * Constructor for @link Sale
     */
    public Sale() {

        items = new ArrayList<>();
        amountPaid = 0;

    }

    /**
     * Adds item to the items list
     * 
     * @param item The item to be added
     */
    public void addItem(Item item) {

        int potentialDuplicate = checkDuplicate(item);

        if (potentialDuplicate < 0)
            items.add(item);
        else
            addToExisting(potentialDuplicate, item);

        calculateTotal();

    }

    private void addToExisting(int index, Item item) {

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
     * Enter amount payed into the sale
     * 
     * @param amount
     */
    public void enterPayment(float amount) {

        amountPaid = amount;

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
     * Getter for the variable totalPrice subtracted with the priceReduction
     * 
     * @return Returns the value of totalPrice subtracted with priceReduction
     */
    public float getDiscountedPrice(){

        return totalPrice - priceReduction;

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

    /**
     * Indexed getter for the list items
     * 
     * @param index The index of the list
     * @return Returns the item at the specified index
     */
    public Item getItem(int index) {

        // if(index < 0 || index >= items.size())
        // TODO throw exception

        return items.get(index);

    }

    /**
     * Getter for the getter size of the items variable
     * 
     * @return Returns the length of the items in the current sale
     */
    public int getItemCount() {

        return items.size();

    }

    /**
     * Getter for the amountPaid variable
     * 
     * @return Returns the value of amountPaid
     */
    public float getAmountPaid() {

        return amountPaid;

    }

    /**
     * Setter for the variable priceReduction
     * @param price The amount the variable priceReduction should be set to
     */
    public void setDiscount(float price){

        priceReduction = price;

    }

    /**
     * Getter for variable priceReduction
     * @return Returns the value of the variable priceReduction
     */
    public float getPriceReduction(){

        return priceReduction;
        
    }

}
