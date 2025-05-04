package se.kth.project.Model;

import se.kth.project.External.InventoryDB;

public class Sale {
    public float runningTotal;
    public Item[] items;
    public int[] count; // Array defining count of each item in items
    private int uniqueItemQuantity;
    private int arraySize;
    private InventoryDB inv;

    public Sale(InventoryDB inv) {
        this.inv = inv;
        this.items = new Item[10];
        this.count = new int[10];
        this.uniqueItemQuantity = 0;
        this.arraySize = 10;
    }

    private void extendItems() {
        Item[] newItems = new Item[this.arraySize + 10];
        int[] newCount = new int[this.arraySize + 10];
        System.arraycopy(this.items, 0, newItems, 0, this.arraySize);
        System.arraycopy(this.count, 0, newCount, 0, this.arraySize);
        this.items = newItems;
        this.count = newCount;
        this.arraySize += 10;
    }

    public boolean addItem(int item, int quantity) {
        if (inv.fetchItem(item) == null) {
            return false; // Item not found in inventory
        }
        for(int i = 0; i < uniqueItemQuantity; i++) {
            if(this.items[i].id == item) {
                this.count[i] += quantity; // Update existing item count
                this.runningTotal += this.items[i].price * quantity;
                return true;
            }
        }
        if (uniqueItemQuantity >= arraySize) {
            extendItems();
        }
        this.items[uniqueItemQuantity] = inv.fetchItem(item);
        this.count[uniqueItemQuantity] = quantity;
        uniqueItemQuantity++;
        this.runningTotal += this.items[uniqueItemQuantity - 1].price * quantity;
        return true;
    }
}