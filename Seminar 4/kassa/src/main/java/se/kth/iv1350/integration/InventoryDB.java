package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.DTO.InventoryDTO;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;

/**
 * Provides @link Item to the @link Controller
 */
public class InventoryDB {

    private List<InventoryDTO> inventory;

    /**
     * Constructor for @link InventoryDB
     */
    public InventoryDB() {

        inventory = new ArrayList<>();
        setUpInventory();

    }

    /**
     * Searches for an @link ItemDTO in the inventory by the itemID
     * 
     * @param itemID Used for the item search
     * @return Returns the @link ItemDTO that corresponds to the itemID
     * @throws InvalidItemIDException This exception is triggered when the itemID was not found in the database
     * @throws NoConnectionException This exception is triggered when there is no connection to the database (in this case itemID = 0)
     */
    public ItemDTO getItemDTO(int itemID) throws InvalidItemIDException, NoConnectionException {

        for (InventoryDTO inventoryDTO : inventory) {

            ItemDTO itemDTO = inventoryDTO.itemDTO();

            if(itemDTO == null)
                continue;

            if (itemDTO.id() == itemID)
                return itemDTO;

        }

        if (itemID == 0)
            throw new NoConnectionException("No connection to the inventoryDB");

        throw new InvalidItemIDException("ItemDTO was not found with the provided itemID: " + itemID);

    }

    /**
     * Takes a sale and reduces the inventory with the items from the sale
     * 
     * @param sale
     */
    public void updateInventory(SaleDTO saleDTO){

        // contact external system

        List<InventoryDTO> items = saleDTO.items();
        int itemCount = items.size();

        for(int i = 0; i < itemCount; i++){

            InventoryDTO inventoryDTO = items.get(i);
            updateItem(inventoryDTO);

        }

    }

    private void updateItem(InventoryDTO inventoryDTO){

        for(int i = 0; i < inventory.size(); i++){

            InventoryDTO inventoryItem = inventory.get(i);

            if(inventoryItem.itemDTO() == inventoryDTO.itemDTO()){

                int newAmount = inventoryItem.count() - inventoryDTO.count();
                inventoryItem = new InventoryDTO(inventoryItem.itemDTO(), newAmount);
                return;

            }

        }

    }

    private void addItemToInventory(InventoryDTO inventoryDTO){

        inventory.add(inventoryDTO);

    }

    private void setUpInventory() {

        addItemToInventory(new InventoryDTO(new ItemDTO(100, "TEST ITEM", 0F, 0F, "TEST ITEM"), 99));
        addItemToInventory(new InventoryDTO(new ItemDTO(101, "BigWheel Oatmeal", 29.9F, 0.06F, "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free"), 99));
        addItemToInventory(new InventoryDTO(new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F, "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour."), 99));

    }

}