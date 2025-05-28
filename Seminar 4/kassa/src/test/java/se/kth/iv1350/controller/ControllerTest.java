package se.kth.iv1350.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Sale;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {

        try{
            controller = new Controller();
            controller.startSale();
            controller.addItem(101, 2);
            controller.addItem(102, 1);
        } catch(Exception e){
            fail(e.getMessage());
        }

    }

    @Test
    public void testEndSale() {

        try{
            controller.endSale();
            controller.enterPayment(9999999f);
            assertTrue(controller.getSale() == null, "Sale did not end properly");
        } catch(Exception e){
            fail(e.getMessage());
        }

    }

    @Test
    public void testAddItem() {

        try{
            controller.addItem(100, 1);
        } catch(Exception e){
            fail(e.getMessage());
        }
        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(100);
        assertTrue(foundItem != null, "Could not find added item");

    }

    @Test
    public void testAddDuplicateItem() {

        try{
            controller.addItem(102, 1);
        } catch(Exception e){
            fail(e.getMessage());
        }
        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(102);
        assertTrue(foundItem != null, "Could not find added duplicate item");

    }

}
