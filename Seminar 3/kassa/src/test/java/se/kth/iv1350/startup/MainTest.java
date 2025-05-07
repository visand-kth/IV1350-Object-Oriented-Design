package se.kth.iv1350.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test script for the @link Main class
 */
public class MainTest {
    
    @Test
    public void testMain(){

        try {
            
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            System.setOut(new PrintStream(content));
            Main.main(null);
            assertTrue(!content.toString().contains("null"), "Main missing components: " + content.toString());

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}
