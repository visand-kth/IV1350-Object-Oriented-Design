package se.kth.project.Model;

import java.util.Scanner;

import se.kth.project.External.DiscountDB;

public class Payment {
    public Sale sale;
    private float discount;
    public float totalCost;
    public float cashPaid;
    /**
     * constructor for Payment object
     * @param sale Sale object containing the items and the 
     * @param accountingDB the accounting database to update to when payment is complete
     * @param discounts discount database to see if person has a discount
     * @param id id of the person
     */
    public Payment(Sale sale, DiscountDB discounts, int id) {
        this.sale = sale;
        this.discount = discounts.calcDiscount(id);
    }

    private float getCash(){
        System.out.println("How much cash is the customer paying?");
        Scanner scanner = new Scanner(System.in);
        float cashPaid = scanner.nextFloat();
        scanner.close();
        return cashPaid;
    }
    /**
     * function which updates inventory and adds sale to accounting db, calculates change and prints reciept
     * @param accounts the database of accounting which holds sale per customer
    */
    public void makePayment() {
        //first make it so that the person gets their discount
        this.totalCost = sale.runningTotal;
        this.totalCost *= 1 - discount;
        this.cashPaid = getCash();
        while(cashPaid < totalCost){
            System.out.println("Thats not enough, try again!");
            this.cashPaid = getCash();
        }
        //make and print receipt
    }
}
