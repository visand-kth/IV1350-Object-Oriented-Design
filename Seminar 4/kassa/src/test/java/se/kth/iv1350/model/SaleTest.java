package se.kth.iv1350.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import se.kth.iv1350.DTO.ItemDTO;
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
        ItemDTO itemDTO = new ItemDTO(100, "TestName", 10F, 0.1F,
                "TEST ITEM description");
        Item newItem = new Item(itemDTO, 1);
        sale.addItem(newItem);
        Item foundItem = sale.findItem(100);
        assertTrue(foundItem != null, "Could not find added item");

    }

    @Test
    public void testAddDuplicateItem() {
        ItemDTO itemDTO = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item newItem = new Item(itemDTO, 1);
        sale.addItem(newItem);
        Item foundItem = sale.findItem(102);
        assertTrue(foundItem != null, "Could not find added duplicate item");
    }

    @Test
    public void testCalculateTotal() {
        ItemDTO itemDTO = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item newItem = new Item(itemDTO, 1);
        sale.addItem(newItem);
        sale.addItem(newItem);
        sale.calculateTotal();
        float total = sale.getTotalPrice();
        float expected = 15.794F;
        assertTrue(total == expected, "Expected price did not match calculated price");
    }

    @Test
    public void testCheckDuplicate() {
        ItemDTO itemDTO1 = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item newItem = new Item(itemDTO1, 1);
        sale.addItem(newItem);
        ItemDTO itemDTO2 = new ItemDTO(101, "BigWheel Oatmeal", 29.9F, 0.06F,
                "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free");
        newItem = new Item(itemDTO2, 2);
        sale.addItem(newItem);
        int duplicate = sale.checkDuplicate(newItem);
        int expected = 1;
        assertTrue(duplicate == expected, "Duplicate was not found");
    }

}
