package se.kth.iv1350.DTO;

/**
 * An DTO describing a store item with its descriptors and values
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 * 
 * @param id          The unique identifier for this item
 * @param name        The name of this item
 * @param price       The price of this item
 * @param vat         The value added tax in factor form should be either 0.06,
 *                    0.12 or 0.25
 * @param description An custom description describing this item
 */
public record ItemDTO(int id, String name, float price, float vat, String description) {

    /**
     * Calculates total price with VAT in consideration
     * 
     * @return Returns the total price
     */
    public float getTotalPrice() {

        return price + getVATPrice();

    }

    /**
     * Calculates VAT based of price
     * 
     * @return Returns the cost of VAT
     */
    public float getVATPrice() {

        return price * vat;

    }

}
