package se.kth.iv1350.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.DTO.InventoryDTO;
import se.kth.iv1350.DTO.SaleDTO;

/**
 * Handles an sale with @link Item and @link payment
 * Creates an @link receipt
 */
public class Sale {

    private SaleDTO saleDTO;
    private List<Item> items;
    private float amountPaid;
    private float priceReduction;
    private float totalPrice;
    private float totalVAT;
    private int customerID;

    /**
     * Constructor for @link Sale
     */
    public Sale() {

        amountPaid = 0;
        priceReduction = 0;
        totalPrice = 0;
        totalVAT = 0;
        customerID = 0;
        items = new ArrayList<>();

    }

    /**
     * Adds item to the items list
     * 
     * @param item The item to be added
     */
    public void addItem(Item item) {

        if (item == null)
            return;

        Item duplicateItem = checkDuplicate(item);

        if (duplicateItem == null)
            items.add(item);
        else
            addToExisting(duplicateItem, item);

        calculateTotal();

    }

    private void addToExisting(Item existingItem, Item item) {

        int currentAmount = existingItem.getAmount();
        int newAmount = currentAmount + item.getAmount();
        existingItem.setAmount(newAmount);

    }

    /**
     * Sums the price of each @link Item in the current @link Sale
     */
    public void calculateTotal() {

        totalPrice = 0;
        totalVAT = 0;

        for (Item item : items) {

            float itemPrice = item.getItemDTO().getTotalPrice() * item.getAmount();
            totalPrice += itemPrice;
            totalVAT += itemPrice * item.getItemDTO().vat();

        }

        List<InventoryDTO> itemsDTO = getInventoryDTO();
        saleDTO = new SaleDTO(itemsDTO, totalPrice, totalVAT, priceReduction, customerID);

    }

    private List<InventoryDTO> getInventoryDTO() {

        List<InventoryDTO> itemsDTO = new ArrayList<>();

        for (Item item : items) {

            itemsDTO.add(new InventoryDTO(item.getItemDTO(), item.getAmount()));

        }

        return itemsDTO;

    }

    /**
     * Checks for duplicate item in the current sale
     * 
     * @param item The item to compare
     * @return The item match of the current sale
     */
    public Item checkDuplicate(Item newItem) {

        for (int i = 0; i < items.size(); i++) {

            Item saleItem = items.get(i);
            int saleItemID = saleItem.getItemDTO().id();
            int newItemID = newItem.getItemDTO().id();

            if (saleItemID == newItemID)
                return saleItem;

        }

        return null;

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
     * Getter for the variable saleDTO
     * 
     * @return Returns the value of the variable saleDTO
     */
    public SaleDTO getSaleDTO() {

        List<InventoryDTO> itemsDTO = getInventoryDTO();
        saleDTO = new SaleDTO(itemsDTO, totalPrice, totalVAT, priceReduction, customerID);
        return saleDTO;

    }

    /**
     * Getter for the variable totalPrice subtracted with the priceReduction
     * 
     * @return Returns the value of totalPrice subtracted with priceReduction
     */
    public float getDiscountedPrice() {

        return saleDTO.totalPrice() - priceReduction;

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

            if (item.getItemDTO().id() == ID)
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

        return items.get(index);

    }

    /**
     * Getter for the getter size of the items variable
     * 
     * @return Returns the length of the items in the current sale
     */
    public int getItemCount() {

        return saleDTO.items().size();

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
     * 
     * @param price The amount the variable priceReduction should be set to
     */
    public void setDiscount(float price) {

        priceReduction = price;

    }

    /**
     * Getter for variable priceReduction
     * 
     * @return Returns the value of the variable priceReduction
     */
    public float getPriceReduction() {

        return priceReduction;

    }

    /**
     * Setter for variable customerID
     * 
     * @param customerID The value to set the variable customerID to
     */
    public void setCustomerID(int customerID) {

        this.customerID = customerID;

    }

}
