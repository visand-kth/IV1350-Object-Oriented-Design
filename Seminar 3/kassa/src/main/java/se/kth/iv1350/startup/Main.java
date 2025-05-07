package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.view.View;

/**
 * Tasked with starting up all the components in the program
 * @author Viktor Sandström
 * @author Adrian Boström
 * @author Dante Solender
 */
public class Main {
    
    /**
     * The main function
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Starting program...");
        System.out.println("Started controller: " + new Controller());
        System.out.println("Started view: " + new View());

    }

}
