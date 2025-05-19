package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Item;
import se.kth.iv1350.DTO.ItemDTO;

/**
 * Handles user input to Controller
 */
public class View {
    private Controller controller;

    /**
     * Constructor for View
     * @param controller The controller to interact with
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Simulates a fake execution of a sale
     */
    public void runFakeExecution() {
        System.out.println("View: Starting a new sale...");
        controller.startSale();

        ItemDTO itemDTO1 = new ItemDTO(101, "BigWheel Oatmeal", 29.9F, 0.06F, "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free");
        Item item1 = new Item(itemDTO1, 2);
        controller.addItem(item1);

        ItemDTO itemDTO2 = new ItemDTO(102, "YouGoGo Blueberry", 14.9F, 0.06F, "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour.");
        Item item2 = new Item(itemDTO2, 1);
        controller.addItem(item2);

        System.out.println("View: Ending the sale...");

        controller.endSale(2); // Assuming 2 is the persons id
    }
}
