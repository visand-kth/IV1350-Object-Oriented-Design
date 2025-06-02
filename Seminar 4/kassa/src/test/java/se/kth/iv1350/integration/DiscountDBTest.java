package se.kth.iv1350.integration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.SaleDTO;

public class DiscountDBTest {
    
    private DiscountDB discountDB;

    @BeforeEach
    public void setUp(){

        discountDB = new DiscountDB();

    }

    @Test
    public void testAddDiscount(){

        try{
            float expected = 0.19F;
            discountDB.addDiscount(1, expected);
            SaleDTO saleDTO = new SaleDTO(new ArrayList<>(), 1, 0, 0, 1);
            float result = 1 - discountDB.checkTotalDiscount(saleDTO);
            assertEquals(expected, result, 0.01f, "Did not find the new discount");
        } catch (Exception e){
            fail("Exception thrown");
        }

    }

    @Test
    public void testCheckTotalInvalidCustomerIDException(){

        try{
            discountDB.checkTotalDiscount(new SaleDTO(new ArrayList<>(), 0, 0, 0, 101));
            fail("Exception was not thrown");
        } catch (InvalidCustomerIDException e){
            ;
        } catch (Exception e){
            fail("Wrong exception thrown");
        }

    }

    @Test
    public void testCheckTotalNoConnectionException(){

        try{
            discountDB.checkTotalDiscount(new SaleDTO(new ArrayList<>(), 0, 0, 0, 0));
            fail("Exception was not thrown");
        } catch (NoConnectionException e){
            ;
        } catch (Exception e){
            fail("Wrong exception thrown");
        }

    }

    @Test
    public void testCheckTotal(){

        try{
            float expected = 600 * 0.9f * 0.95f;
            float result = discountDB.checkTotalDiscount(new SaleDTO(new ArrayList<>(), 600, 0, 0, 201));
            if(expected != result) fail("Result did not match expected value");
            expected = 1600 * 0.9f * 0.9f;
            result = discountDB.checkTotalDiscount(new SaleDTO(new ArrayList<>(), 1600, 0, 0, 201));
            assertEquals(expected, result, 0.1f, "Result did not match expected value");
        } catch (Exception e){
            fail("Exception thrown");
        }

    }

}
