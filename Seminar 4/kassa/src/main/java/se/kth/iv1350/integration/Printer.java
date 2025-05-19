package se.kth.iv1350.integration;

import se.kth.iv1350.model.Receipt;

public class Printer {
    /**
     * Constructor for @link Printer
     */
    public Printer() {
        System.out.println("Starting printer...");
    }

    /**
     * Prints the receipt of a sale
     */
    public void printReceipt() {
        System.out.println("Printing receipt...");
    }

    public void print(Receipt receipt) {
        System.out.println(receipt.toString()); // Or a more detailed printout
    }
}
