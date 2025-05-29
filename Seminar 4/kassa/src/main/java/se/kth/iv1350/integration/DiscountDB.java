package se.kth.iv1350.integration;

import java.util.HashMap;
import java.util.List;

import se.kth.iv1350.DTO.InventoryDTO;
import se.kth.iv1350.DTO.SaleDTO;

/**
 * The discount database that provides the discounts to the @link Controller.
 * 
 * This implementation uses the Strategy Pattern from GoF in order to improve
 * readability
 * the interfaces and extra classes that are related to this pattern are kept in
 * this file.
 * If this would be a severe program design flaw they could be moved to their
 * own files
 * and imported.
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class DiscountDB {

    private HashMap<Integer, Float> discounts;

    /**
     * Constructor for @link DiscountDB
     */
    public DiscountDB() {

        discounts = new HashMap<Integer, Float>();
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
     * Searches for the total discounts of an sale with the specified userID
     * 
     * @param saleDTO The current saleDTO
     * @return Returns the total price to be reduced from the sale with all
     *         discounts
     */
    public float checkTotalDiscount(SaleDTO saleDTO)
            throws InvalidCustomerIDException, NoConnectionException {

        DiscountContext customerDiscount = new DiscountContext(new CustomerDiscount());
        DiscountContext priceDiscount = new DiscountContext(new PriceDiscount());
        DiscountContext itemCountDiscount = new DiscountContext(new ItemCountDiscount());

        float price = 1;

        price *= 1 - customerDiscount.getDiscount(discounts, saleDTO);
        price *= 1 - priceDiscount.getDiscount(discounts, saleDTO);
        price *= 1 - itemCountDiscount.getDiscount(discounts, saleDTO);

        return saleDTO.totalPrice() * price;

    }

}

/**
 * A Strategy Design Pattern for different types of discounts encapsulates all
 * discounts for good encapsulation
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
class DiscountContext {

    private DiscountType discountType;

    /**
     * Constructor for @link DiscountContext
     * 
     * @param discountType The @link DiscountType of this specific discount
     */
    public DiscountContext(DiscountType discountType) {

        this.discountType = discountType;

    }

    /**
     * Getter for the specific discount to calculate its percentage discount
     * 
     * @param discounts The list of customer discounts
     * @param saleDTO   The DTO of the current sale to calculate discount from
     * @return Returns a factor of price reduction
     * @throws NoConnectionException      Thrown when there is no connection to the
     *                                    discount database (our case customerID =
     *                                    0)
     * @throws InvalidCustomerIDException Thrown when customerID cannot be found in
     *                                    the database
     */
    public float getDiscount(HashMap<Integer, Float> discounts, SaleDTO saleDTO)
            throws NoConnectionException, InvalidCustomerIDException {

        return discountType.getDiscount(discounts, saleDTO);

    }

}

/**
 * The interface applying the getDiscount function on the discount types
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
interface DiscountType {

    float getDiscount(HashMap<Integer, Float> discounts, SaleDTO saleDTO)
            throws NoConnectionException, InvalidCustomerIDException;

}

/**
 * This class implements the @link DiscountType to get the discount for specific
 * customers in the discount database
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
class CustomerDiscount implements DiscountType {

    /**
     * Searches for a discount with the customerID in the database
     * 
     * @param discounts The list of customer discounts
     * @param saleDTO   The DTO of the current sale to calculate discount from
     * @return Returns a factor of price reduction found in the database
     * @throws NoConnectionException      Thrown when there is no connection to the
     *                                    discount database (our case customerID =
     *                                    0)
     * @throws InvalidCustomerIDException Thrown when customerID cannot be found in
     *                                    the database
     */
    @Override
    public float getDiscount(HashMap<Integer, Float> discounts, SaleDTO saleDTO)
            throws NoConnectionException, InvalidCustomerIDException {

        int customerID = saleDTO.customerID();

        if (customerID == 0)
            throw new NoConnectionException("No connection to the discountDB");

        if (discounts.containsKey(customerID))
            return discounts.get(customerID);

        throw new InvalidCustomerIDException("Customer ID was not found: " + customerID);

    }

}

/**
 * This class implements the @link DiscountType to calculate a discount
 * depending on the sale price, a price greater than 500 results in a 5%
 * discount and greater than 1500 results in a 10% discount
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
class PriceDiscount implements DiscountType {

    /**
     * Calculates the price of a discount depending on the sale price, a price
     * greater than 500 results in a 5% discount and greater than 1500 results in a
     * 10% discount
     * 
     * @param discounts The list of customer discounts
     * @param saleDTO   The DTO of the current sale to calculate discount from
     * @return Returns a factor of price reduction
     * @throws NoConnectionException      Thrown when there is no connection to the
     *                                    discount database (our case customerID =
     *                                    0)
     * @throws InvalidCustomerIDException Thrown when customerID cannot be found in
     *                                    the database
     */
    @Override
    public float getDiscount(HashMap<Integer, Float> discounts, SaleDTO saleDTO)
            throws NoConnectionException, InvalidCustomerIDException {

        int customerID = saleDTO.customerID();

        if (customerID == 0)
            throw new NoConnectionException("No connection to the discountDB");

        float price = saleDTO.totalPrice();

        if (price > 1500)
            return 0.1f;

        if (price > 500)
            return 0.05f;

        return 0;

    }

}

/**
 * This class implements the @link DiscountType to calculate a discount
 * depending on the total amount of items in the customer's sale, greater or
 * equal to 10 items result in a 5% discounts and
 * reater or equal to 20 items result in a 10% discount
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
class ItemCountDiscount implements DiscountType {

    /**
     * Calculates the price of a discount depending on the total amount of items in
     * the customer's sale, greater or equal to 10 items result in a 5% discounts
     * and reater or equal to 20 items result in a 10% discount
     * 
     * @param discounts The list of customer discounts
     * @param saleDTO   The DTO of the current sale to calculate discount from
     * @return Returns a factor of price reduction
     * @throws NoConnectionException      Thrown when there is no connection to the
     *                                    discount database (our case customerID =
     *                                    0)
     * @throws InvalidCustomerIDException Thrown when customerID cannot be found in
     *                                    the database
     */
    @Override
    public float getDiscount(HashMap<Integer, Float> discounts, SaleDTO saleDTO)
            throws NoConnectionException, InvalidCustomerIDException {

        int customerID = saleDTO.customerID();

        if (customerID == 0)
            throw new NoConnectionException("No connection to the discountDB");

        int itemCount = getItemCount(saleDTO.items());

        if (itemCount >= 20)
            return 0.1f;
        if (itemCount >= 10)
            return 0.05f;

        return 0;

    }

    private int getItemCount(List<InventoryDTO> itemList) {

        int itemCount = 0;

        for (InventoryDTO inventoryDTO : itemList) {

            itemCount += inventoryDTO.count();

        }

        return itemCount;

    }

}