package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemDTO;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides ItemDTO to the Controller and manages stock.
 */
public class InventoryDB {
    private final Map<Integer, ItemDTO> items = new HashMap<>();
    private final Map<Integer, Integer> stock = new HashMap<>();

    /**
     * Constructor for InventoryDB
     * Adds some sample items to the inventory.
     */
    public InventoryDB() {
        // Example items with initial stock
        items.put(101, new ItemDTO(101, "BigWheel Oatmeal", 29.9F, 0.06F, "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free"));
        stock.put(101, 10); // 10 units in stock

        items.put(102, new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F, "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour."));
        stock.put(102, 15); // 15 units in stock
    }

    /**
     * Finds an item by its ID.
     * @param id The item ID.
     * @return The ItemDTO if found.
     * @throws ItemNotFoundException if the item is not found.
     */
    public ItemDTO findItem(int id) throws ItemNotFoundException, DatabaseFailureException {
        if (id == 999) {
            throw new DatabaseFailureException("Database is not available for item ID: " + id);
        }
        if (!items.containsKey(id)) {
            throw new ItemNotFoundException(id);
        }
        return items.get(id);
    }

    /**
     * Gets the current stock for an item.
     * @param id The item ID.
     * @return The stock quantity, or 0 if not found.
     */
    public int getStock(int id) {
        return stock.getOrDefault(id, 0);
    }

    /**
     * Decreases the stock for an item.
     * @param id The item ID.
     * @param amount The amount to decrease.
     * @return true if successful, false if not enough stock.
     */
    public boolean decreaseStock(int id, int amount) {
        int currentStock = stock.getOrDefault(id, 0);
        if (currentStock < amount) {
            return false; // Not enough stock
        }
        stock.put(id, currentStock - amount);
        return true;
    }

    /**
     * Adds a new item to the inventory.
     * @param item The ItemDTO to add.
     * @param initialStock The initial stock quantity.
     */
    public void addItem(ItemDTO item, int initialStock) {
        items.put(item.getID(), item);
        stock.put(item.getID(), initialStock);
    }

     /**
     * Updates (decreases) the stock for an item in the inventory.
     * @param item The ItemDTO whose stock should be decreased.
     * @param amount The amount to decrease.
     * @return true if successful, false if not enough stock or item doesn't exist.
     */
    public boolean updateItem(ItemDTO item, int amount) {
        int id = item.getID();
        int currentStock = stock.getOrDefault(id, 0);
        if (currentStock < amount) {
            return false; // Not enough stock
        }
        stock.put(id, currentStock - amount);
        return true;
    }
}