package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Sale;

/**
 * Provides @link Item to the @link Controller
 */
public class InventoryDB {

    private List<ItemDTO> itemDTOList;
    private List<Item> inventory;

    /**
     * Constructor for @link InventoryDB
     */
    public InventoryDB() {

        itemDTOList = new ArrayList<>();
        inventory = new ArrayList<>();
        setUpInventory();

    }

    /**
     * Searches for an @link ItemDTO in the inventory by the itemID
     * 
     * @param itemID Used for the item search
     * @return Returns the @link ItemDTO that corresponds to the itemID
     * @throws InvalidItemIDException
     * @throws NoConnectionException
     */
    public ItemDTO getItemDTO(int itemID) throws InvalidItemIDException, NoConnectionException {

        for (Item item : inventory) {

            if(item == null)
                continue;

            ItemDTO itemDTO = item.getItemDTO();

            if(itemDTO == null)
                continue;

            if (itemDTO.getID() == itemID)
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
    public void updateInventory(Sale sale){

        int itemCount = sale.getItemCount();

        for(int i = 0; i < itemCount; i++){

            Item item = sale.getItem(i);
            updateItem(item);


        }

    }

    private void updateItem(Item item){

        for(int i = 0; i < inventory.size(); i++){

            Item inventoryItem = inventory.get(i);

            if(inventoryItem.getItemDTO() == item.getItemDTO()){

                int newAmount = inventoryItem.getAmount() - item.getAmount();
                inventoryItem.setAmount(newAmount);
                return;

            }

        }

    }

    private void addItemToInventory(Item item){

        inventory.add(item);

    }

    private void setUpInventory() {

        addItemToInventory(new Item(new ItemDTO(100, "TEST ITEM", 0F, 0F, "TEST ITEM"), 99));
        addItemToInventory(new Item(new ItemDTO(101, "BigWheel Oatmeal", 29.9F, 0.06F, "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free"), 99));
        addItemToInventory(new Item(new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F, "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour."), 99));

    }

}