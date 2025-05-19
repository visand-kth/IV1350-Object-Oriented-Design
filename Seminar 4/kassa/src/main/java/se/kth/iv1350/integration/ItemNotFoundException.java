package se.kth.iv1350.integration;

/**
 * Thrown when an item identifier does not exist in the inventory.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates a new instance with a message describing the error.
     * @param itemId The item identifier that was not found.
     */
    public ItemNotFoundException(int itemId) {
        super("Item with ID " + itemId + " was not found in the inventory.");
    }
}