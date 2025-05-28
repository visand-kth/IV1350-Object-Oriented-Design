package se.kth.iv1350.view;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.controller.Controller;

public class ViewTest {
    
    private View view;
    private Controller controller;

    @Test
    public void testView() {

        try{
            controller = new Controller();
            view = new View(controller);
        } catch (Exception e){
            fail("Exception thrown");
        }

    }

}
