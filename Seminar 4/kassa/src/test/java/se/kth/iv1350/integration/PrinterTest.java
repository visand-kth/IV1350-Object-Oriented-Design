package se.kth.iv1350.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.InventoryDTO;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;

public class PrinterTest {
    
    private Printer printer;

    @BeforeEach
    public void setUp(){

        printer = new Printer();

    }

    @Test
    public void testPrint(){

        try{

            List<InventoryDTO> inventoryDTOList = new ArrayList<>();
            ItemDTO testItem1 = new ItemDTO(9901, "TESTITEM1", 11.0f, 0.03f, "TEST ITEM");
            inventoryDTOList.add(new InventoryDTO(testItem1, 3));
            ItemDTO testItem2 = new ItemDTO(9902, "TESTITEM2", 17.0f, 0.03f, "TEST ITEM");
            inventoryDTOList.add(new InventoryDTO(testItem2, 2));
            ItemDTO testItem3 = new ItemDTO(9903, "TESTITEM3", 8.0f, 0.06f, "TEST ITEM");
            inventoryDTOList.add(new InventoryDTO(testItem3, 6));
            float totalPrice = 11.0f * 3 * 1.03f + 17.0f * 2 * 1.03f + 8.0f * 6 * 1.06f;
            float totalVAT = 11.0f * 3 * 0.03f + 17.0f * 2 * 0.03f + 8.0f * 6 * 0.06f;
            float payment = 1000;
            float priceReduction = 10;
            SaleDTO saleDTO = new SaleDTO(inventoryDTOList, totalPrice, totalVAT, priceReduction, 9900);

            PrintStream originalOut = System.out;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(stream);
            System.setOut(out);
        
            printer.print(saleDTO, payment);

            System.setOut(originalOut);

            assertTrue(stream.toString().contains("TESTITEM1\t3 x 11,33\t33,99 SEK"), "Printer wrong TESTITEM1");
            assertTrue(stream.toString().contains("TESTITEM2\t2 x 17,51\t35,02 SEK"), "Printer wrong TESTITEM2");
            assertTrue(stream.toString().contains("TESTITEM3\t6 x 8,48\t50,88 SEK"), "Printer wrong TESTITEM3");
            assertTrue(stream.toString().contains("Total: \t\t\t\t\t119,89 SEK"), "Printer wrong total");
            assertTrue(stream.toString().contains("VAT: 4,89"), "Printer wrong VAT");
            assertTrue(stream.toString().contains("Discount: \t\t\t\t-10,00 SEK"), "Printer wrong discount");
            assertTrue(stream.toString().contains("Final: \t\t\t\t\t109,89 SEK"), "Printer wrong final");
            assertTrue(stream.toString().contains("Cash: \t\t\t\t\t1000,00 SEK"), "Printer wrong cash");
            assertTrue(stream.toString().contains("Change: \t\t\t\t890,11 SEK"), "Printer wrong change");

        } catch(Exception e){
            fail("Exception thrown");
        }

    }

}
