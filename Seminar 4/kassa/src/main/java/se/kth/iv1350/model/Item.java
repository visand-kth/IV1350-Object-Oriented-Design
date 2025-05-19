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
     * Constructor for Item, default amount is 1, if not specified
     * @param itemDTO ItemDTO object containing the item information
     */
    public Item(ItemDTO itemDTO) {

        this.itemDTO = itemDTO;
        this.amount = 1;
    }

    /**
     * adds to the amount of the item
     * 
     * @return Returns the new amount of the item
     */
    public int addItem(int amount) {

        this.amount += amount;
        return this.amount;
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
     * @return Returns the itemDTO
     */
    public ItemDTO getItemDTO() {
        return itemDTO;
    }

}
