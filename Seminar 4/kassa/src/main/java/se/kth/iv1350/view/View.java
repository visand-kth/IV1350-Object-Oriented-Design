package se.kth.iv1350.view;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
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
    private void runUserSimulation(){

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

        startSale();
        addItem(101, 1);
        addItem(101, 3);
        addItem(102, 5);
        addItem(101, 7);
        addItem(101, 9);
        requestDiscount(212);
        endSale();
        enterPayment(1000);

        startSale();
        addItem(0, 1);
        endSale();
        requestDiscount(201);
        enterPayment(100);

        startSale();
        addItem(101, 1);
        endSale();
        requestDiscount(0);
        enterPayment(100);

    }

    private void addItem(int itemID, int amount){

        System.out.println(String.format("[VIEW] Add %d item with item id %d:\n", amount, itemID));

        try {
            ItemDTO itemDTO = controller.addItem(itemID, amount);
            SaleDTO saleDTO = controller.getSaleDTO();
            System.out.println(String.format("\tItemID: %d\n\tItem name: %s\n\tItem cost: %.2f SEK\n\tVAT: %.2f%%\n\tItem description: %s\n\n\tTotal cost (incl VAT): %.2f SEK\n\tTotal VAT: %.2f SEK.\n\n", 
            itemDTO.name(), itemDTO.name(), itemDTO.getTotalPrice(), itemDTO.vat() * 100, itemDTO.description(), saleDTO.totalPrice(), saleDTO.totalVAT()));
        } catch (Exception e) {
            System.out.println("[VIEW] There was a problem adding the item.");
        }

    }

    private void startSale(){

        controller.startSale();

    }

    private void enterPayment(float payment){

        try{
            controller.enterPayment(payment);
        } catch(Exception e){
            System.out.println("[VIEW] There was a problem entering the payment.");
        }

    }

    private void endSale(){

        System.out.println("[VIEW] End sale:");
        SaleDTO saleDTO = controller.endSale();
        System.out.println(String.format("Total: \t\t\t\t\t%.2f SEK.", saleDTO.totalPrice()));
        System.out.println(String.format("VAT: %.2f.", saleDTO.totalVAT()));

    }

    private void requestDiscount(int userID){

        try{
            controller.requestDiscount(userID);
        } catch(Exception e){
            System.out.println("[VIEW] There was a problem with the discount.");
        }

    }
    
}
