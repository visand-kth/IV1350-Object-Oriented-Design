package se.kth.project.External;
import se.kth.project.Model.Item;
import se.kth.project.Model.Sale;

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
     * Function that updates the inventory database stock count from a sale object
     * @param sale Sale object that contains the items and their quantities to be updated
     */
    public void updateInventory(Sale sale) {
        for(int i = 0; i < sale.items.length; i++){
            int index = getIndex(sale.items[i].id);
            if(index == -1) System.out.println("Tried to remove an item that doesnt exist in db");
            this.stock[index] -= sale.count[i]; 
            if(this.stock[index] < 0) System.out.println("negative stock? (American stocks moment)");
        }
    }
}
