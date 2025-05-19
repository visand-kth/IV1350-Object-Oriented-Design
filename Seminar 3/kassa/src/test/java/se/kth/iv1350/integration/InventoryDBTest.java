package se.kth.iv1350.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.ItemDTO;

public class InventoryDBTest {
    
    private InventoryDB inventoryDB;

    @BeforeEach
    public void setUp(){

        inventoryDB = new InventoryDB();

    }

    @Test
    public void testGetItemDTO(){

        ItemDTO itemDTO = null;

        try{
            itemDTO = inventoryDB.getItemDTO(101);
        } catch (Exception e){
            fail(e.getMessage());
        }

        assertTrue(itemDTO != null, "Could not find the itemDTO");

    }

}
