package se.kth.iv1350.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.integration.DatabaseFailureException;
import se.kth.iv1350.integration.InventoryDB;
import se.kth.iv1350.integration.ItemNotFoundException;

public class ControllerTest {

    private Controller controller;
    private InventoryDB inventoryDB;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
        controller.startSale();
        inventoryDB = new InventoryDB();
        try {
            ItemDTO testItemDTO = inventoryDB.findItem(101);
            ItemDTO testItemDTOtwo = inventoryDB.findItem(102);
            Item testItem = new Item(testItemDTO, 2);
            Item testItemtwo = new Item(testItemDTOtwo, 2);

            controller.addItem(testItem);
            controller.addItem(testItemtwo);
        } catch (Exception e) {
            fail("Exception caught: " + e.getMessage());
        }
    }

    @Test
    public void testEndSale() {
        controller.endSale(2); // Assuming 2 is the persons id
        assertTrue(controller.getSale() == null, "Sale did not end properly");
    }

    @Test
    public void testAddItem() {
        try{
        ItemDTO testDTO = inventoryDB.findItem(101);
        Item newItem = new Item(testDTO, 1);
        controller.addItem(newItem);

        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(101);

        assertTrue(foundItem != null, "Could not find added item");
        } catch (Exception e) {
            fail("Exception caught: " + e.getMessage());
        }
    }

    @Test
    public void testAddDuplicateItem() {
        try{
        ItemDTO testDTO = inventoryDB.findItem(102);
        Item newItem = new Item(testDTO, 1);
        controller.addItem(newItem);

        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(102);

        assertTrue(foundItem != null, "Could not find added duplicate item");
        } catch (Exception e) {
            fail("Exception caught: " + e.getMessage());
        }
    }

    @Test
    public void testFindInvalidItemId() {
        try {
            ItemDTO testDTO = inventoryDB.findItem(999); // 999 does not exist
            assertTrue(testDTO == null, "Should return null for invalid item ID");
        } catch (Exception e) {
            assertTrue(e instanceof DatabaseFailureException, "Expected DatabaseFailureException");
        }   
    }
}
