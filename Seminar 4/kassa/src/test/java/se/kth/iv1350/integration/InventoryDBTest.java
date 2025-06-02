package se.kth.iv1350.integration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;

public class InventoryDBTest {
    
    private InventoryDB inventoryDB;

    @BeforeEach
    public void setUp(){

        inventoryDB = new InventoryDB();

    }

    @Test
    public void testGetItemDTOInvalidItemIDException(){

        try{
            inventoryDB.getItemDTO(1);
            fail("Exception not thrown");
        } catch (InvalidItemIDException e){
            ;
        } catch (Exception e){
            fail("Wrong exception thrown");
        }

    }

    @Test
    public void testGetItemDTONoConnectionException(){

        try{
            inventoryDB.getItemDTO(0);
            fail("Exception not thrown");
        } catch (NoConnectionException e){
            ;
        } catch (Exception e){
            fail("Wrong exception thrown");
        }

    }

    @Test
    public void testGetItemDTO(){

        try {
            ItemDTO itemDTO = inventoryDB.getItemDTO(100);
            assertTrue(itemDTO != null, "ItemDTO not found");
        } catch (Exception e) {
            fail("Exception thrown");
        }

    }

    @Test
    public void testUpdateInventory(){

        try{
            SaleDTO saleDTO = new SaleDTO(new ArrayList<>(), 0, 0, 0, 0);
            inventoryDB.updateInventory(saleDTO);
        } catch (Exception e){
            fail("Exception thrown");
        }
            
    }

}
