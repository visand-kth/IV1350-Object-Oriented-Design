package se.kth.iv1350.view;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.controller.Controller;

/**
 * Handles the user input to the program
 */
public class View {
    
    private Controller controller;

    /**
     * Constructor for @link View
     */
    public View(Controller controller){
        
        this.controller = controller;
        runUserSimulation();

    }

    /**
     * Simulates an user using the program
     */
    public void runUserSimulation(){

        startSale();
        addItem(101, 1);
        addItem(102, 1);
        addItem(101, 3);
        endSale();
        requestDiscount(201);
        enterPayment(500);

        startSale();
        addItem(105, 1);
        addItem(104, 3);
        addItem(103, 5);
        addItem(102, 7);
        addItem(101, 9);
        endSale();
        enterPayment(1000);

    }

    private void addItem(int itemID, int amount){

        System.out.println(String.format("Add %d item with item id %d:\n", amount, itemID));

        try {
            
            controller.addItem(itemID, amount);
            ItemDTO itemDTO = controller.getSale().findItem(itemID).getItemDTO();

            System.out.println(String.format("ItemID: %d\nItem name: %s\nItem cost: %.2f SEK\nVAT: %.2f%%\nItem description: %s\n\nTotal cost (incl VAT): %.2f SEK\nTotal VAT: %.2f SEK\n\n", 
            itemDTO.getID(), itemDTO.getName(), itemDTO.getTotalPrice(), itemDTO.getVAT() * 100, itemDTO.getDescription(), controller.getSale().getTotalPrice(), controller.getSale().getTotalVAT()));

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    private void startSale(){

        controller.startSale();

    }

    private void enterPayment(float payment){

        controller.enterPayment(payment);

    }

    private void endSale(){

        System.out.println("End sale:");
        controller.endSale();
        
    }

    private void requestDiscount(int userID){

        controller.requestDiscount(userID);

    }
    
}
