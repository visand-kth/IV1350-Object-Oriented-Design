public class InventoryDB {
    private Item[] db;
    private int[] stock;
    
    /**
     * Constructor for inventory database
     */
    public InventoryDB() {
        db = new Item[10];
        stock = new int[10];
    }
    private int getIndex(int itemID){
        for(int i = 0; i < db.length; i++){
            if(db[i].equals(null))break;
            if(db[i].id == itemID) return i;
        }
        return -1;
    }
    /**
     * 
     * @param itemID item ID
     * @return either returns Item if found or null if invalid
     */
    public Item fetchItem(int itemID) {
        for(int i = 0; i < this.db.length; i++){
            if(this.db[i].id == itemID){
                Item item = this.db[i];
                return item;
            }
        }
        return null;
    }
    /**
     * Function that updates the inventory database stock count for a specific Item
     * @param itemID id of the item to remove
     * @param count quantity of item to remove
     */
    public void updateInventory(int itemID, int count) {
        int index = getIndex(itemID);
        if(index == -1) System.out.println("Tried to remove an item that doesnt exist in db");
        this.stock[index] -= count; 
        if(this.stock[index] < 0) System.out.println("negative stock? (American stocks moment)");
    }
}
