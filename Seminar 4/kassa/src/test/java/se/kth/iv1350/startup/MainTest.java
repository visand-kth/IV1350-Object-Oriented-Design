package se.kth.iv1350.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Test script for the @link Main class
 */
public class MainTest {

    @Test
    public void testMain() {

        try{

        PrintStream originalOut = System.out;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(stream);
        System.setOut(out);
        Main.main(null);
        System.setOut(originalOut);
        assertTrue(!stream.toString().contains("null"), "Could not complete the startup");

        } catch (Exception e){
            fail("Exception thrown");
        }

    }

}
