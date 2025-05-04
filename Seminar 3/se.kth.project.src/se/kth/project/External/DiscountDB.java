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
        // Create DiscountDB
        for(int i = 0; i < this.ids.length; i++){
            if(this.ids[i] == id){
                return discounts[i];
            }
        }
        //error id doesnt exist
        return 0;
    }
    /**
     * adding customer to the database, assumed that this is a function call made by management or smth so that the discount amount is variable
     * @param id number to uniquely identify the person, maybe personal number or smth
     * @param discount float between 0-1 where e.g. 0.2 means 20% discount
     */
    public void addCustomer (int id, float discount)
    {
        int[] newIDs = new int[this.ids.length+1];
        float[] newDiscounts = new float[this.discounts.length+1];
        for(int i = 0; i < this.ids.length; i++){
            newIDs[i] = this.ids[i];
            newDiscounts[i] = this.discounts[i];
        }
        this.ids = newIDs;
        this.discounts = newDiscounts;
    }
}