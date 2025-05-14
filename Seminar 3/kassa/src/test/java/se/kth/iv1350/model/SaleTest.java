package se.kth.iv1350.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Sale;

public class SaleTest {

    private Sale sale;

    @BeforeEach
    public void setUp() {

        sale = new Sale();

    }

    @Test
    public void testAddItem() {

        Item newItem = new Item(100, 1, "TESTER", 10F, 0.1F, "TEST ITEM");
        sale.addItem(newItem);
        Item foundItem = sale.findItem(100);
        assertTrue(foundItem != null, "Could not find added item");

    }

    @Test
    public void testAddDuplicateItem() {

        Item newItem = new Item(102, 1, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        sale.addItem(newItem);
        Item foundItem = sale.findItem(102);
        assertTrue(foundItem != null, "Could not find added duplicate item");

    }

    @Test
    public void testCalculateTotal() {

        Item newItem = new Item(102, 1, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        sale.addItem(newItem);
        sale.addItem(newItem);
        sale.calculateTotal();
        float total = sale.getTotalPrice();
        float expected = 15.794F;
        assertTrue(total == expected, "Expected price did not match calculated price");

    }

    @Test
    public void testCheckDuplicate() {

        Item newItem = new Item(102, 1, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        sale.addItem(newItem);
        newItem = new Item(101, 2, "BigWheel Oatmeal", 29.9F, 0.06F,
                "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free");
        sale.addItem(newItem);
        int duplicate = sale.checkDuplicate(newItem);
        int expected = 1;
        assertTrue(duplicate == expected, "Duplicate was not found");

    }

}
