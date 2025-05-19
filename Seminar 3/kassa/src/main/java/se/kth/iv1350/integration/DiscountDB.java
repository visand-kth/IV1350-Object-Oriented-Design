package se.kth.iv1350.integration;

import java.util.HashMap;

import se.kth.iv1350.model.Sale;

/**
 * The discount database that provides the discounts to the @link Controller
 */
public class DiscountDB {

    private HashMap<Integer, Float> discounts;

    /**
     * Constructor for @link DiscountDB
     */
    public DiscountDB() {

        discounts = new HashMap<>();
        addDiscountedCustomers();

    }

    private void addDiscountedCustomers() {

        addDiscount(201, 0.1f);
        addDiscount(205, 0.05f);
        addDiscount(208, 0.2f);
        addDiscount(211, 0.1f);

    }

    /**
     * Add discount depending on the userID
     * 
     * @param userID   The userID the discount is linked to
     * @param discount The percentage to be reduced from the sale
     */
    public void addDiscount(int userID, float discount) {

        discounts.put(userID, discount);

    }

    /**
     * Checks for possible customer discounts
     * 
     * @param userID The customer ID
     * @return Returns the discount for the specific customer if such discount
     *         exists
     */
    private float checkCustomerDiscount(int userID) {

        if (discounts.containsKey(userID))
            return discounts.get(userID);

        return 0;

    }

    /**
     * Returns a 10% discount for a price greater than 1500 and 5% for a price
     * greater than 500
     * 
     * @param sale The current sale
     * @return Returns a percentage discount if such is eligible
     */
    private float checkPriceDiscount(Sale sale) {

        float price = sale.getTotalPrice();

        if (price > 1500)
            return 0.1f;

        if (price > 500)
            return 0.05f;

        return 0;

    }

    /**
     * Searches for the total discounts of an sale with the specified userID
     * 
     * @param userID The ID of the customer
     * @param sale   The current sale
     * @return Returns the total price to be reduced from the sale with all
     *         discounts
     */
    public float checkTotalDiscount(int userID, Sale sale) {

        float price = 1;

        price *= 1 - checkPriceDiscount(sale);
        price *= 1 - checkCustomerDiscount(userID);

        return sale.getTotalPrice() * (1 - price);

    }

}
