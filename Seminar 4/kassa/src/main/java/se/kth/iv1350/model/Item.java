package se.kth.iv1350.model;

import se.kth.iv1350.DTO.ItemDTO;

/**
 * Handles the items from the @link InventoryDB
 */
public class Item {

    private ItemDTO itemDTO;
    private int amount;

    /**
     * Constructor for Item
     * 
     * @param itemDTO ItemDTO object containing the item information
     * @param amount  The amount of the item
     */
    public Item(ItemDTO itemDTO, int amount) {

        this.itemDTO = itemDTO;
        this.amount = amount;
        
    }
    /**
     * Constructor for Item, default amount is 1
     * @param itemDTO ItemDTO object containing the item information
     */
    public Item(ItemDTO itemDTO) {

        this.itemDTO = itemDTO;
        this.amount = 1;
    }

    /**
     * Getter for the variable itemDTO
     * 
     * @return Returns the value of itemDTO
     */
    public void addItem(int amount) {

        this.amount += amount;

    }
    
    /**
     * Getter for the variable amount
     * 
     * @return Returns the value of amount
     */
    public int getAmount() {

        return amount;

    }

    /**
     * Getter for the variable itemDTO
     * 
     * @return Returns the value of itemDTO
     */
    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    /**
     * Prints the item to the console
     */
    public void print() {

        System.out.println("Item: " + itemDTO.getName() + "\n" +
                "Price: " + itemDTO.getPrice() + "\n" +
                "Amount: " + amount + "\n" +
                "Total price: " + itemDTO.getTotalPrice() + "\n");
    }

}
