package se.kth.iv1350.model;

import se.kth.iv1350.DTO.ItemDTO;

/**
 * Handles the items from the @link InventoryDB
 */
public class Item {

    private ItemDTO itemDTO;
    private int amount;

    /**
     * Constructor for @link Item
     * 
     * @param price The cost of the @link Item
     */
    public Item(ItemDTO itemDTO, int amount) {

        this.itemDTO = itemDTO;
        this.amount = amount;

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
     * Getter for the variable amount
     * 
     * @return Returns the value of amount
     */
    public int getAmount() {

        return amount;

    }

}
