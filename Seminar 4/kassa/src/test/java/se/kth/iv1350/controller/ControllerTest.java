package se.kth.iv1350.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.DTO.ItemDTO;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {

        controller = new Controller();
        controller.startSale();
        Item testItem, testItemtwo;
        ItemDTO testItemDTO, testItemDTOtwo;
        testItemDTO = new ItemDTO(101,"BigWheel Oatmeal", 29.9F, 0.06F,
                "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free");
        testItemDTOtwo = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        testItem = new Item(testItemDTO, 2);
        testItemtwo = new Item(testItemDTOtwo, 2);
        System.out.println("Add " + testItem.getAmount() + " item with item id " + testItem.getItemDTO().getID() + ":");
        controller.addItem(testItem);
        System.out.println("Add " + testItemtwo.getAmount() + " item with item id " + testItemtwo.getItemDTO().getID() + ":");
        controller.addItem(testItemtwo);
        System.out.println("Add 1 item with item id 102:");
        System.out.println("Add " + testItemtwo.getAmount() + " item with item id " + testItemtwo.getItemDTO().getID() + ":");
        

    }

    @Test
    public void testEndSale() {

        controller.endSale();
        assertTrue(controller.getSale() == null, "Sale did not end properly");

    }

    @Test
    public void testAddItem() {

        ItemDTO testDTO = new ItemDTO(100, "TESTER", 10F, 0.1F, "TEST ITEM");
        Item newItem = new Item(testDTO, 1);
        controller.addItem(newItem);
        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(100);
        assertTrue(foundItem != null, "Could not find added item");

    }

    @Test
    public void testAddDuplicateItem() {

        ItemDTO testDTO = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F,
                "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item newItem = new Item(testDTO, 1);
        controller.addItem(newItem);
        Sale sale = controller.getSale();
        Item foundItem = sale.findItem(102);
        assertTrue(foundItem != null, "Could not find added duplicate item");

    }

}
