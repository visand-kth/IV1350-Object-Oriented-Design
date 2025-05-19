package se.kth.iv1350.integration;

import java.util.List;
import java.util.ArrayList;

import se.kth.iv1350.model.Sale;

/**
 * Accounting database for @link Controller
 */
public class AccountingDB {
    
    private List<Sale> accounting;

    /**
     * Constructor for @link AccountingDB
     */
    public AccountingDB(){

        accounting = new ArrayList<>();

    }

    /**
     * Saves the specified sale to the accounting database
     * @param sale The specific sale to save to the database
     */
    public void saveSale(Sale sale){

        accounting.add(sale);

    }

}
