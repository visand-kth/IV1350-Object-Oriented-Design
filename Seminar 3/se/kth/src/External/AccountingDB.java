package se.kth.src.External;
import se.kth.src.Model.Receipt;

/**
 * Class that represents the accounting database, it is assumed that this is a class that is used to save the sales made by the customers and to get the receipts of a specific customer
 * @author Adrian Boström
 * @author Dante Solender
 * @author Viktor Sandström
 */
public class AccountingDB {
    private int[] ids;
    private Receipt[][] receipts; 
    /**
     * Constructor for the accounting database, class that is used to save the sales made by the customers and to get the receipts of a specific customer
     */
    public AccountingDB() {
        ids = new int[100];
        receipts = new Receipt[100][];
    }
    /**
     * Function that saves the sale to the database, assumed that this is a function call made by management or smth so that the discount amount is variable
     * @param id number to uniquely identify the person, maybe personal number or smth
     * @param receipt receipt object to be saved
     */
    public void saveSale(int id, Receipt receipt) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == id) {
                if (receipts[i] == null) {
                    receipts[i] = new Receipt[1];
                } else {
                    Receipt[] temp = new Receipt[receipts[i].length + 1];
                    System.arraycopy(receipts[i], 0, temp, 0, receipts[i].length);
                    receipts[i] = temp;
                }
                receipts[i][receipts[i].length - 1] = receipt;
                break;
            }
        }
    }
    /**
     * Function that returns the receipts of a specific customer
     * @param id id of the customer to get receipts from
     * @return array of receipts or null if not found
     */
    public Receipt[] getReceipts(int id) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == id) {
                return receipts[i];
            }
        }
        return null;
    }
    /**
     * adding customer to the database
     * @param id number to uniquely identify the person, maybe personal number or smth
     */
    public void addCustomer(int id) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == 0) {
                ids[i] = id;
                break;
            }
        }
    }
}
