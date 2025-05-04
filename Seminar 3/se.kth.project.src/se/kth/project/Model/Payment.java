package se.kth.project.Model;

import java.util.Scanner;

import se.kth.project.External.DiscountDB;

public class Payment {
    public Sale sale;
    private float discount;
    public float totalCost;
    public float cashPaid;

    /**
     * Constructor for Payment object
     * @param sale Sale object containing the items
     * @param discounts Discount database to see if the person has a discount
     * @param id ID of the person
     */
    public Payment(Sale sale, DiscountDB discounts, int id) {
        this.sale = sale;
        this.discount = discounts.calcDiscount(id);
    }

    private float getCash(Scanner scanner) {
        System.out.println("How much cash is the customer paying?");
        return scanner.nextFloat();
    }

    /**
     * Function which updates inventory and adds sale to accounting DB, calculates change, and prints receipt
     */
    public void makePayment() {
        // First make it so that the person gets their discount
        this.totalCost = sale.runningTotal;
        this.totalCost *= 1 - discount;
        Scanner scanner = new Scanner(System.in);
        this.cashPaid = getCash(scanner);
        while (cashPaid < totalCost) {
            System.out.println("That's not enough, try again!");
            this.cashPaid = getCash(scanner);
        }
    }
}
