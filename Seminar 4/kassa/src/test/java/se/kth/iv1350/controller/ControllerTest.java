package se.kth.iv1350.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.integration.InvalidCustomerIDException;
import se.kth.iv1350.integration.InvalidItemIDException;
import se.kth.iv1350.integration.NoConnectionException;
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

    @Test
    public void testInvalidItemIDException(){

        try {
            controller.addItem(1,1);
            fail("No exception thrown");
        } catch(InvalidItemIDException e){
            assertTrue(true, "");
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }

    }

    @Test
    public void testAddItemNoConnectionException(){

        try {
            controller.addItem(0,1);
            fail("No exception thrown");
        } catch (NoConnectionException e) {
            ;
        } catch (Exception e){
            fail("Wrong exception thrown");
        }

    }

    @Test
    public void testEnterPayment(){

        try {
            float expected = 11.1f;
            Sale sale = controller.getSale();
            controller.enterPayment(expected);
            float result = sale.getAmountPaid();
            assertTrue(expected == result, "Wrong payment value");
        } catch (Exception e) {
            fail("Exception thrown");
        }

    }

    @Test
    public void testRequestDiscount(){

        try {
            controller.endSale();
            controller.requestDiscount(201);
            float expected = (29.9f * 2 * 1.06f + 14.9f * 1.06f) * 0.9f;
            float result = controller.getSaleDTO().priceReduction();
            assertEquals(expected, result, 0.1f, "Wrong total discount value");
        } catch (Exception e) {
            fail("Exception thrown");
        }

    }

    @Test
    public void testInvalidCustomerIDException(){

        try {
            controller.endSale();
            controller.requestDiscount(101);
            fail("No exception thrown");
        } catch (InvalidCustomerIDException e) {
            ;
        } catch (Exception e){
            fail("Wrong exception thrown");
        }

    }

    @Test
    public void testRequestDiscountNoConnectionException(){

        try {
            controller.endSale();
            controller.requestDiscount(0);
            fail("No exception thrown");
        } catch (NoConnectionException e) {
            ;
        } catch (Exception e){
            fail("Wrong exception thrown");
        }

    }

}
