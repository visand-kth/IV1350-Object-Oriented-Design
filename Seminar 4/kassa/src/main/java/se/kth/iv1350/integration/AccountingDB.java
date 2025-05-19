package se.kth.iv1350.integration;

import se.kth.iv1350.model.Receipt;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Accounting database for Controller.
 * Stores receipts linked to a person's ID number.
 */
public class AccountingDB {
    private final Map<Integer, List<Receipt>> receiptsByPersonId = new HashMap<>();

    /**
     * Constructor for AccountingDB
     * Adds some example receipts for demonstration.
     */
    public AccountingDB() {
        // Example: Create dummy receipts and assign them to example person IDs
        List<Receipt> receipts1 = new ArrayList<>();
        List<Receipt> receipts2 = new ArrayList<>();

        // These would be real Receipt objects in a real system.
        // Here we use null or you can create mock Receipt objects if needed.
        receipts1.add(new Receipt(null)); // Replace null with a dummy Sale if needed
        receipts2.add(new Receipt(null));
        receipts2.add(new Receipt(null));

        receiptsByPersonId.put(12345, receipts1);
        receiptsByPersonId.put(67890, receipts2);
    }

    /**
     * Stores a receipt linked to a person's ID number.
     * @param personId The person's ID number.
     * @param receipt The receipt to store.
     */
    public void storeReceipt(int personId, Receipt receipt) {
        if (personId == 0) {
            throw new IllegalArgumentException("Person ID cannot be 0.");
        }
        if (receipt == null) {
            throw new IllegalArgumentException("Receipt cannot be null.");
        }

        List<Receipt> receipts = receiptsByPersonId.get(personId);
        if (receipts == null) {
            receipts = new ArrayList<>();
            receiptsByPersonId.put(personId, receipts);
        }
        receipts.add(receipt);
    }

    /**
     * Retrieves all receipts for a given person's ID number.
     * @param personId The person's ID number.
     * @return List of receipts for the person, or empty list if none found.
     */
    public List<Receipt> getReceiptsForPerson(int personId) {
        if (personId == 0) {
            throw new IllegalArgumentException("Person ID cannot be 0.");
        }

        List<Receipt> receipts = receiptsByPersonId.get(personId);
        if (receipts == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(receipts); // Return a copy to prevent modification
    }
}
