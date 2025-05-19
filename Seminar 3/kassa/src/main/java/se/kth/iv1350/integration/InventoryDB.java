package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.DTO.ItemDTO;

/**
 * Provides @link Item to the @link Controller
 */
public class InventoryDB {
    
    private List<ItemDTO> itemDTOList;

    /**
     * Constructor for @link InventoryDB
     */
    public InventoryDB(){

        itemDTOList = new ArrayList<>();
        setUpInventory();

    }

    /**
     * Searches for an @link ItemDTO in the inventory by the itemID
     * @param itemID Used for the item search
     * @return Returns the @link ItemDTO that corresponds to the itemID
     */
    public ItemDTO getItemDTO(int itemID) throws InvalidItemIDException, NoConnectionException{

        for(ItemDTO itemDTO : itemDTOList){

            if(itemDTO.getID() == itemID)
                return itemDTO;

        }

        if(itemID == 0)
            throw new NoConnectionException("No connection to the inventoryDB");

        throw new InvalidItemIDException("ItemDTO was not found with the provided itemID: " + itemID);

    }

    private void addItemDTO(ItemDTO itemDTO){

        itemDTOList.add(itemDTO);

    }

    private void setUpInventory(){

        addItemDTO(new ItemDTO(100, "TEST ITEM", 0F, 0F, "TEST ITEM"));
        addItemDTO(new ItemDTO(101, "BigWheel Oatmeal", 29.9F, 0.06F, "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free"));
        addItemDTO(new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F, "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour."));

    }

}