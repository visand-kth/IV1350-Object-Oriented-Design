package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.view.View;

/**
 * Tasked with starting up all the components in the program
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class Main {

    /**
     * The main function
     * 
     * @param args No arguments are used in this program
     */
    public static void main(String[] args) {

        System.out.println("[STARTUP] Starting program");
        Controller controller = new Controller();
        System.out.println("[STARTUP] Controller started: " + controller);
        View view = new View(controller);
        System.out.println("[STARTUP] View started: " + view);
        System.out.println("[STARTUP] Running user simulation");
        view.runUserSimulation();

    }

}
