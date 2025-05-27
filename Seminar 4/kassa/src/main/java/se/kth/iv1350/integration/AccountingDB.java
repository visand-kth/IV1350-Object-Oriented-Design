package se.kth.iv1350.integration;

import java.time.LocalDateTime;
import java.util.HashMap;

import se.kth.iv1350.DTO.RecordDTO;
import se.kth.iv1350.DTO.SaleDTO;

/**
 * Accounting database for @link Controller
 */
public class AccountingDB {
    
    private HashMap<Integer, RecordDTO> accounting;

    /**
     * Constructor for @link AccountingDB
     */
    public AccountingDB(){

        accounting = new HashMap<>();

    }

    /**
     * Saves the specified sale to the accounting database
     * @param saleDTO The specific sale to save to the database
     */
    public void saveSale(SaleDTO saleDTO){

        LocalDateTime dateTime = LocalDateTime.now();
        RecordDTO recordDTO = new RecordDTO(saleDTO, dateTime);
        accounting.put(saleDTO.customerID(), recordDTO);

    }

}
