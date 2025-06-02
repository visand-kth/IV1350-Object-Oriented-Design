package se.kth.iv1350.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.controller.Controller;

public class ViewTest {
    
    private View view;
    private Controller controller;

    @BeforeEach
    public void setUp(){

        controller = new Controller();
        view = new View(controller);
        
    }

    @Test
    public void testViewSimulation() {

        try{
            view.runUserSimulation();
        } catch (Exception e){
            fail("Exception thrown");
        }

    }

    @Test
    public void testPrintouts(){

        try {

            PrintStream originalOut = System.out;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(stream);
            System.setOut(out);

            view.runUserSimulation();
            
            System.setOut(originalOut);
            String[] commands = stream.toString().split("\\[VIEW\\] ");
            String[] lines = commands[1].split("\n");

            assertTrue(lines[2].equals("\tItemID: 101"), "AddItem wrong ItemID");
            assertTrue(lines[3].equals("\tItem name: BigWheel Oatmeal"), "AddItem wrong Item name");
            assertTrue(lines[4].equals("\tItem cost: 31,69 SEK"), "AddItem wrong Item cost");
            assertTrue(lines[5].equals("\tVAT: 6,00%"), "AddItem wrong VAT");
            assertTrue(lines[6].equals("\tItem description: BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free"), "AddItem wrong Item description");
            assertTrue(lines[8].equals("\tTotal cost (incl VAT): 31,69 SEK"), "AddItem wrong Total cost (incl VAT)");
            assertTrue(lines[9].equals("\tTotal VAT: 1,90 SEK."), "AddItem wrong Total VAT");

            lines = commands[4].split("\\r?\\n");

            assertTrue(lines[1].equals("Total: \t\t\t\t\t142,57 SEK."), "ShowTotal wrong Total");
            assertTrue(lines[2].equals("VAT: 8,55."), "ShowTotal wrong VAT");

            lines = commands[6].split("\\r?\\n");

            assertTrue(lines[0].equals("ItemID was not found."), "ItemID was found but should have not been");

            lines = commands[19].split("\\r?\\n");

            assertTrue(lines[0].equals("CustomerID was not found."), "CustomerID was found but should have not been");

        } catch (Exception e) {
            fail("Exception thrown");
        }

    }

}
