package se.kth.iv1350.integration;

import java.util.HashMap;
import java.util.Map;

/**
 * The discount database that provides the discounts to the @link Controller
 */
public class DiscountDB {
    private final Map<Integer, Float> discountsByPersonId = new HashMap<>();

    /**
     * Constructor for DiscountDB
     * Adds some sample discounts.
     */
    public DiscountDB() {
        discountsByPersonId.put(12345, 0.10f);
        discountsByPersonId.put(67890, 0.20f);
    }

    /**
     * Gets the discount for a given person ID.
     * @param personId The person's ID number.
     * @return The discount as a float (e.g., 0.10 for 10%), or 0 if none found.
     */
    public float getDiscount(int personId) {
        if (personId == 0) {
            throw new IllegalArgumentException("Person ID cannot be null or empty.");
        }
        Float discount = discountsByPersonId.get(personId);
        if (discount == null) {
            return 0f;
        }
        return discount;
    }

    /**
     * Adds or updates a discount for a person ID.
     * @param personId The person's ID number.
     * @param discount The discount as a float (e.g., 0.15 for 15%).
     */
    public void setDiscount(int personId, float discount) {
        if (personId == 0) {
            throw new IllegalArgumentException("Person ID cannot be null or empty.");
        }
        if (discount < 0f || discount > 1f) {
            throw new IllegalArgumentException("Discount must be between 0 and 1.");
        }
        discountsByPersonId.put(personId, discount);
    }
}
