package se.kth.project.External;
public class DiscountDB {
    private int[] ids;
    private float[] discounts;
    
    public DiscountDB() {
        this.ids = new int[10];
        this.discounts = new float[10];
        // Create DiscountDB
    }
    
    public float calcDiscount(int id) {
        for (int i = 0; i < this.ids.length; i++) {
            if (this.ids[i] == id) { // Found the customer ID
                return this.discounts[i]; // Return the discount for that customer
            }
        }
        return 0; // ID does not exist
    }
    /**
     * adding customer to the database, assumed that this is a function call made by management or smth so that the discount amount is variable
     * @param id number to uniquely identify the person, maybe personal number or smth
     * @param discount float between 0-1 where e.g. 0.2 means 20% discount
     */
    public void addCustomer(int id, float discount) {
        // Check if the customer already exists

        // Find the first empty slot in the arrays
        for (int i = 0; i < this.ids.length; i++) {
            if (this.ids[i] == 0) { // Empty slot found
                this.ids[i] = id;
                this.discounts[i] = discount;
                return;
            }
        }
        int[] newIDs = new int[this.ids.length + 10];
        float[] newDiscounts = new float[this.discounts.length + 10];
        for (int i = 0; i < this.ids.length; i++) {
            newIDs[i] = this.ids[i];
            newDiscounts[i] = this.discounts[i];
        }
        this.ids = newIDs;
        this.discounts = newDiscounts;

        // Add the new customer to the resized arrays
        this.ids[this.ids.length - 10] = id;
        this.discounts[this.discounts.length - 10] = discount;
    }
}