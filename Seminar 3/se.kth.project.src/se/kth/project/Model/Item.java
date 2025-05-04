package se.kth.project.Model;

public class Item {
    public float price;
    public int id;
    public String description;
    public float vat;
    public Item(float price, int id, String description, float vat){
        // Create Item
        this.price = price;       
        this.id = id;       
        this.description = description;       
        this.vat = vat;
    }
    /**
     * This function is called when adding an item manually
     * @param price - Specifies price of product including vat  
     */
    public Item(float price){
        // Create Item
        this.price = price;       
        this.id = 0;       
        this.description = "Manually added item";       
        this.vat = 0;
    }
}
