package se.kth.iv1350.integration;

import java.util.HashMap;

import se.kth.iv1350.DTO.SaleDTO;

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
     * @throws InvalidCustomerIDException This exception is triggered when the customerID was not found in the database
     * @throws NoConnectionException This exception is triggered when there is no connection to the database (in this case userID = 0)
     */
    public float checkCustomerDiscount(int userID) throws InvalidCustomerIDException, NoConnectionException {

        if(userID == 0)
            throw new NoConnectionException("No connection to the discountDB");

        if (discounts.containsKey(userID))
            return discounts.get(userID);

        throw new InvalidCustomerIDException("Customer ID was not found: " + userID);

    }

    /**
     * Returns a 10% discount for a price greater than 1500 and 5% for a price
     * greater than 500
     * 
     * @param saleDTO The current saleDTO
     * @return Returns a percentage discount if such is eligible
     */
    private float checkPriceDiscount(SaleDTO saleDTO) {

        float price = saleDTO.totalPrice();

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
     * @param saleDTO The current saleDTO
     * @return Returns the total price to be reduced from the sale with all
     *         discounts
     */
    public float checkTotalDiscount(int userID, SaleDTO saleDTO) throws InvalidCustomerIDException, NoConnectionException{

        float price = 1;

        price *= 1 - checkPriceDiscount(saleDTO);
        price *= 1 - checkCustomerDiscount(userID);

        return saleDTO.totalPrice() * (1 - price);

    }

}
