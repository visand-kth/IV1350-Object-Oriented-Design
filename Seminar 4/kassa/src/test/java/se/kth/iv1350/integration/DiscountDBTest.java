package se.kth.iv1350.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiscountDBTest {
    
    private DiscountDB discountDB;

    @BeforeEach
    public void setUp(){

        discountDB = new DiscountDB();

    }

    @Test
    public void testAddDiscount(){

        float expected = 0.19F;
        discountDB.addDiscount(1, expected);
        float result = 0;
        try{
            result = discountDB.checkCustomerDiscount(1);
        } catch (Exception e){
            fail(e.getMessage());
        }
        assertTrue(expected == result, "Did not find the new discount");

    }

    @Test
    public void testAddDiscountException(){

        try{
            discountDB.checkCustomerDiscount(0);
            fail("Exception was not thrown");
        } catch (Exception e){
            assertTrue(true, "Exception thrown when tested for discount");
        }

    }

}
