package se.kth.iv1350.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Sale;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {

        controller = new Controller();
        controller.startSale();
        controller.addItem(101, 2);
        controller.addItem(102, 1);

    }

    @Test
    public void testEndSale() {

        controller.endSale();
        controller.enterPayment(9999999f);
        assertTrue(controller.getSale() == null, "Sale did not end properly");

    }

    @Test
    public void testAddItem() {

        controller.addItem(100, 1);
        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(100);
        assertTrue(foundItem != null, "Could not find added item");

    }

    @Test
    public void testAddDuplicateItem() {

        controller.addItem(102, 1);
        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(102);
        assertTrue(foundItem != null, "Could not find added duplicate item");

    }

}
