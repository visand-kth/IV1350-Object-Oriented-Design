package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.DTO.SaleDTO;

/**
 * Accounting database for @link Controller
 */
public class AccountingDB {
    
    private List<SaleDTO> accounting;

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
    public void saveSale(SaleDTO saleDTO){

        accounting.add(saleDTO);

    }

}
