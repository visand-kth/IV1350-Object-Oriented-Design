package se.kth.iv1350.DTO;

public record ItemDTO (int id, String name, float price, float vat, String description){
    
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
