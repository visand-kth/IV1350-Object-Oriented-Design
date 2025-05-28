package se.kth.iv1350.startup;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test script for the @link Main class
 */
public class MainTest {

    @Test
    public void testMain() {

        Main.main(null);
        assertTrue(true, "Could not complete the startup");

    }

}
