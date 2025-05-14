package se.kth.iv1350.DTO;

public class ItemDTO {
    
    private int id;
    private String name;
    private float price;
    private float vat;
    private String description;

    public ItemDTO(int id, String name, float price, float vat, String description){

        this.id = id;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.description = description;

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
     * @return Returns the total price, price + VAT
     */
    public float getTotalPrice() {

        return price + getVATPrice();

    }

    /**
     * Getter for the variable vat
     * 
     * @return Returns the value of vat, %
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
