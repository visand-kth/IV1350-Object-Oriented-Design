package se.kth.iv1350.integration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.SaleDTO;

public class AccountingDBTest {

    private AccountingDB accountingDB;

    @BeforeEach
    public void setUp() {

        accountingDB = new AccountingDB();

    }

    @Test
    public void testSaveSale(){

        try {
            accountingDB.saveSale(new SaleDTO(new ArrayList<>(), 0, 0, 0, 201));
            assertTrue(true);
        } catch (Exception e) {
            fail("Exception thrown");
        }

    }

}
