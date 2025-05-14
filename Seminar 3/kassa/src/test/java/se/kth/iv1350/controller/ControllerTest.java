package se.kth.iv1350.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Sale;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {

        controller = new Controller();
        controller.startSale();
        Item testItem;
        testItem = new Item(101, 2, "BigWheel Oatmeal", 29.9F, 0.06F,
                "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free");
        System.out.println("Add 2 item with item id 101:");
        controller.addItem(testItem);
        System.out.println("Add 2 item with item id 101:");
        controller.addItem(testItem);
        testItem = new Item(102, 1, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        System.out.println("Add 1 item with item id 102:");
        controller.addItem(testItem);

    }

    @Test
    public void testEndSale() {

        controller.endSale();
        assertTrue(controller.getSale() == null, "Sale did not end properly");

    }

    @Test
    public void testAddItem() {

        Item newItem = new Item(100, 1, "TESTER", 10F, 0.1F, "TEST ITEM");
        controller.addItem(newItem);
        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(100);
        assertTrue(foundItem != null, "Could not find added item");

    }

    @Test
    public void testAddDuplicateItem() {

        Item newItem = new Item(102, 1, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        controller.addItem(newItem);
        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(102);
        assertTrue(foundItem != null, "Could not find added duplicate item");

    }

}
