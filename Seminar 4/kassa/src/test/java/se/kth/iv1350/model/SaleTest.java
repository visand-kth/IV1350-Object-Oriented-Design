package se.kth.iv1350.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.integration.ItemNotFoundException;
import se.kth.iv1350.view.TotalRevenueView;
import se.kth.iv1350.view.TotalRevenueLogging;
import se.kth.iv1350.view.TotalRevenueObserver;

public class SaleTest {
    private Sale sale;
    private List<TotalRevenueObserver> observers;

    @BeforeEach
    public void setUp() {
        observers = new java.util.ArrayList<>();
        TotalRevenueObserver viewObserver = new TotalRevenueView();
        TotalRevenueObserver logObserver = new TotalRevenueLogging();
        this.observers.add(viewObserver);
        this.observers.add(logObserver);
        // Call newSale on both observers with a test value
        sale = new Sale(observers);
    }

    @Test
    public void testAddItem() {
        try {
            ItemDTO itemDTO = new ItemDTO(101, "BigWheel Oatmeal", 29.9F, 0.06F,
                    "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free");
            Item newItem = new Item(itemDTO, 1);
            sale.addItem(newItem);
            Item foundItem = sale.findItem(101);
            assertTrue(foundItem != null, "Could not find added item");
        } catch (Exception e) {
            assertTrue(false, "Exception caught: " + e.getMessage());
        }
    }

    @Test
    public void testAddDuplicateItem() {
        try {
            ItemDTO itemDTO = new ItemDTO(105, "BigWheel Oatmeal", 29.9F, 0.06F,
                    "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free");
            Item newItem = new Item(itemDTO, 1);
            sale.addItem(newItem);
            sale.addItem(newItem);
            Item foundItem = sale.findItem(105);
            assertTrue(foundItem != null, "Could not find added duplicate item");
        } catch (Exception e) {
            assertTrue(false, "Exception caught: " + e.getMessage());
        }
    }

    @Test
    public void testCalculateTotal() {
        try {
            ItemDTO itemDTO = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item newItem = new Item(itemDTO, 1);
        sale.addItem(newItem);
        sale.addItem(newItem);
        sale.calculateTotal();
        float total = sale.getTotalPrice();
        float expected = 15.794F;
        assertTrue(total == expected, "Expected price did not match calculated price");
        } catch (Exception e) {
            assertTrue(false, "Exception caught: " + e.getMessage());
        }
    }

    @Test
    public void testCheckDuplicate() {
        try{
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
        }catch (Exception e) {
            assertTrue(false, "Exception caught: " + e.getMessage());
        }
        
    }

    @Test
    public void testFindNonExistentItem() {
        try {
            ItemDTO itemDTO = new ItemDTO(200, "NonExistentTest", 20F, 0.2F,
                    "Non-existent item description");
            Item newItem = new Item(itemDTO, 1);
            sale.addItem(newItem);
            Item foundItem = sale.findItem(999); // Non-existent item ID
            assertTrue(foundItem == null, "Found non-existent item");
        } catch (Exception e) {
            assertTrue(e instanceof ItemNotFoundException, "Expected ItemNotFoundException");
        }
    }

}
