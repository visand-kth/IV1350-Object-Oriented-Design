package se.kth.iv1350.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp(){

        controller = new Controller();

    }
    
    @Test
    public void testSale(){

        controller.startSale();

    }

}
