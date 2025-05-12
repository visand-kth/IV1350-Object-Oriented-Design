package se.kth.iv1350.model;

/**
 * Handles the items from the @link InventoryDB
 */
public class Item {

    private int id;
    private int amount;
    private String name;
    private float price;
    private float vat;
    private String description;

    /**
     * Constructor for @link Item
     * 
     * @param price The cost of the @link Item
     */
    public Item(int id, int amount, String name, float price, float vat, String description) {

        this.id = id;
        this.amount = amount;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.description = description;

    }

    /**
     * Prints the item and its values
     */
    public void print() {

        System.out.println(String.format("Item ID: %d", id));
        System.out.println(String.format("Item name: %s", name));
        System.out.println(String.format("Item cost: %.2f SEK", getTotalPrice()));
        System.out.println(String.format("Item VAT: %.2f%%", vat * 100));
        System.out.println(String.format("Item description: %s", description));

    }

    /**
     * Getter for the variable id
     * 
     * @return Returns the value of id
     */
    public int getID() {

        return id;

    }

    /**
     * Getter for the variable amount
     * 
     * @return Returns the value of amount
     */
    public int getAmount() {

        return amount;

    }

    /**
     * Getter for the variable name
     * 
     * @return Returns the value of name
     */
    public String getName() {

        return name;

    }

    /**
     * Getter for the variable price
     * 
     * @return Returns the value of price
     */
    public float getPrice() {

        return price;

    }

    /**
     * Calculates total price with VAT in consideration
     * 
     * @return Returns the total price
     */
    public float getTotalPrice() {

        return price + getVATPrice();

    }

    /**
     * Getter for the variable vat
     * 
     * @return Returns the value of vat
     */
    public float getVAT() {

        return vat;

    }

    /**
     * Calculates VAT based of price
     * 
     * @return Returns the cost of VAT
     */
    public float getVATPrice() {

        return price * vat;

    }

    /**
     * Getter for the variable description
     * 
     * @return Returns the value of description
     */
    public String getDescription() {

        return description;

    }

}
