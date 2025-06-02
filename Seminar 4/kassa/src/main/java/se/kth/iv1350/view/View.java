package se.kth.iv1350.view;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.InvalidCustomerIDException;
import se.kth.iv1350.integration.InvalidItemIDException;
import se.kth.iv1350.integration.NoConnectionException;
import se.kth.iv1350.integration.TotalRevenueFileOutput;
import se.kth.iv1350.integration.TotalRevenueFileOutputTemplate;
import se.kth.iv1350.model.TotalRevenueObserver;
import se.kth.iv1350.util.FileLogger;

/**
 * Handles the user input to the program
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class View {

    private Controller controller;
    private FileLogger fileLogger;
    private List<TotalRevenueObserver> totalRevenueObservers;

    /**
     * Constructor for @link View
     * 
     * @param controller The controller that the view communicates to
     */
    public View(Controller controller) {

        this.controller = controller;
        fileLogger = new FileLogger();
        totalRevenueObservers = new ArrayList<>();
        totalRevenueObservers.add(new TotalRevenueView());
        totalRevenueObservers.add(new TotalRevenueFileOutput());
        totalRevenueObservers.add(new TotalRevenueViewTemplate());
        totalRevenueObservers.add(new TotalRevenueFileOutputTemplate());

    }

    /**
     * Simulates a actions on the view from a user
     */
    public void runUserSimulation() {

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

    private void addItem(int itemID, int amount) {

        try {
            System.out.println(String.format("[VIEW] Add %d item with item id %d:\n", amount, itemID));
            ItemDTO itemDTO = controller.addItem(itemID, amount);
            SaleDTO saleDTO = controller.getSaleDTO();
            System.out.println(String.format(
                    "\tItemID: %d\n\tItem name: %s\n\tItem cost: %.2f SEK\n\tVAT: %.2f%%\n\tItem description: %s\n\n\tTotal cost (incl VAT): %.2f SEK\n\tTotal VAT: %.2f SEK.\n\n",
                    itemDTO.id(), itemDTO.name(), itemDTO.getTotalPrice(), itemDTO.vat() * 100, itemDTO.description(),
                    saleDTO.totalPrice(), saleDTO.totalVAT()));
        } catch (InvalidItemIDException e) {
            System.out.println("[VIEW] ItemID was not found.");
            fileLogger.logException(e);
        } catch (NoConnectionException e) {
            System.out.println("[VIEW] No connection to the database.");
            fileLogger.logException(e);
        } catch (Exception e) {
            System.out.println("[VIEW] There was an unexpected problem adding the item.");
            fileLogger.logException(e);
        }

    }

    private void startSale() {

        try {
            controller.startSale();
        } catch (Exception e) {
            System.out.println("[VIEW] There was an unexpected problem starting the sale.");
            fileLogger.logException(e);
        }

    }

    private void enterPayment(float payment) {

        try {
            notifyObservers();
            controller.enterPayment(payment);
        } catch (Exception e) {
            System.out.println("[VIEW] There was an unexpected problem entering payment.");
            fileLogger.logException(e);
        }

    }

    private void endSale() {

        try {
            System.out.println("[VIEW] End sale:");
            SaleDTO saleDTO = controller.endSale();
            System.out.println(String.format("Total: \t\t\t\t\t%.2f SEK.", saleDTO.totalPrice()));
            System.out.println(String.format("VAT: %.2f.", saleDTO.totalVAT()));
        } catch (Exception e) {
            System.out.println("[VIEW] There was an unexpected problem terminating the sale.");
            fileLogger.logException(e);
        }

    }

    private void requestDiscount(int userID) {

        try {
            controller.requestDiscount(userID);
        } catch (InvalidCustomerIDException e) {
            System.out.println("[VIEW] CustomerID was not found.");
            fileLogger.logException(e);
        } catch (NoConnectionException e) {
            System.out.println("[VIEW] No connection to the database.");
            fileLogger.logException(e);
        } catch (Exception e) {
            System.out.println("[VIEW] There was an unexpected problem with the discount.");
            fileLogger.logException(e);
        }

    }

    private void notifyObservers() {

        try {
            for (TotalRevenueObserver totalRevenueObserver : totalRevenueObservers) {

                SaleDTO saleDTO = controller.getSaleDTO();
                float revenue = saleDTO.totalPrice() - saleDTO.priceReduction();
                totalRevenueObserver.addSale(revenue);

            }
        } catch (Exception e) {
            fileLogger.logException(e);
        }

    }

}
