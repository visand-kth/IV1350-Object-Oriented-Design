package se.kth.iv1350.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.ItemDTO;

public class SaleTest {

    private Sale sale;

    @BeforeEach
    public void setUp() {

        sale = new Sale();

    }

    @Test
    public void testAddItem() {

        ItemDTO itemDTO = new ItemDTO(100, "TEST", 0F, 0F, "TEST ITEM");
        Item item = new Item(itemDTO, 1);
        sale.addItem(item);
        Item foundItem = sale.findItem(100);
        assertTrue(foundItem != null, "Could not find added item");

    }

    @Test
    public void testAddDuplicateItem() {

        ItemDTO itemDTO = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item item = new Item(itemDTO, 1);
        sale.addItem(item);
        Item foundItem = sale.findItem(102);
        assertTrue(foundItem != null, "Could not find added duplicate item");

    }

    @Test
    public void testCalculateTotal() {

        ItemDTO itemDTO = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item item = new Item(itemDTO, 1);
        sale.addItem(item);
        sale.addItem(item);
        sale.calculateTotal();
        float total = sale.getSaleDTO().totalPrice();
        float expected = 31.588F;
        assertTrue(total == expected, "Expected price did not match calculated price");

    }

    @Test
    public void testCheckDuplicate() {

        ItemDTO itemDTO = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item item = new Item(itemDTO, 1);
        sale.addItem(item);
        itemDTO = new ItemDTO(10, "BigWheel Oatmeal", 29.9F, 0.06F,
                "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free");
        item = new Item(itemDTO, 2);
        sale.addItem(item);
        Item duplicate = sale.checkDuplicate(item);
        Item expected = item;
        assertTrue(duplicate == expected, "Duplicate was not found");

    }

}
