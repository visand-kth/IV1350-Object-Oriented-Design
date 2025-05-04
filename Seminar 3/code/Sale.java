public class Sale {
    public float runningTotal;
    public Item[] items;
    public int[] count; //an array defining count of each item in items
    private int uniqueItemQuantity;
    private int arraySize;
    private InventoryDB inv;
    public Sale(InventoryDB inv) {
        this.arraySize = 10;
        this.items = new Item[arraySize]; // Initialize the array with a size of arraySize
        this.count = new int[arraySize];
        this.runningTotal = 0; //initialised to 0
        this.uniqueItemQuantity = 0; 
        this.inv = inv;
        // Create Sale
    }
    /**
     * This function extends the items array and count array to be bigger
     */
    private void extendItems(){
        Item[] newItems = new Item[arraySize*=2];
        int[] newCount = new int[arraySize];
        for(int i = 0; i < uniqueItemQuantity; i++){
            newItems[i] = this.items[i];
            newCount[i] = this.count[i];
        }
        this.items = newItems;
        this.count = newCount;
    }
    /**
     * function gets index of the item in the current sale, will return -1 if not found
     * @param item - Item which it needs to find the index of
     */
    private int getIndex(Item item){
        for(int i = 0; i < arraySize; i++){
            if(this.items[i].equals(item)) return i;
        }
        //if none of the items match it then return -1
        return -1;
    }
    /**
     * adds item to the sale given an itemID and quantity
     * @param itemID id of the item
     * @param quantity quantity to add to the sale
     */
    public boolean addItem(int itemID, int quantity) {
        Item newItem = inv.fetchItem(itemID);
        //if couldnt find item it will return null
        if(newItem.equals(null)) return false;
        //if item already exists add quantity
        int index;
        if((index = getIndex(newItem)) != -1){
            this.count[index] += quantity;
            this.runningTotal += quantity * newItem.price;
            return true;
        }
        // if array is full
        if(uniqueItemQuantity - 1 == arraySize){
            extendItems();
        }
        this.items[uniqueItemQuantity] = newItem;
        this.count[uniqueItemQuantity++] = quantity;
        this.runningTotal += quantity*newItem.price;
        return true;
    }
}