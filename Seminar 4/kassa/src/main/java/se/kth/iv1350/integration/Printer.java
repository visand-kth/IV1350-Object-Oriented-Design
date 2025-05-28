package se.kth.iv1350.integration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import se.kth.iv1350.DTO.InventoryDTO;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;

/**
 * Prints an receipt of an @link Sale on an external printer
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class Printer {

    /**
     * Constructor for @link Receipt
     */
    public Printer() {

    }

    /**
     * Creates and prints a receipt on the physical machine.
     * 
     * @param saleDTO The saleDTO to be printed
     * @param payment How much was paid to this sale
     */
    public void print(SaleDTO saleDTO, float payment) {

        int saleLength = saleDTO.items().size();
        System.out.println("------------------ Begin receipt -------------------");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println(String.format("Time of Sale: %s\n\n", time));
        List<InventoryDTO> saleItems = saleDTO.items();

        for (int i = 0; i < saleLength; i++) {

            InventoryDTO saleItem = saleItems.get(i);
            ItemDTO itemDTO = saleItem.itemDTO();
            int itemCount = saleItem.count();
            System.out.println(String.format("%s\t%d x %.2f\t%.2f SEK", itemDTO.name(), itemCount,
                    itemDTO.getTotalPrice(), itemDTO.getTotalPrice() * itemCount));

        }

        System.out.println(String.format("\nTotal: \t\t\t\t\t%.2f SEK", saleDTO.totalPrice()));
        System.out.println(String.format("VAT: %.2f", saleDTO.totalVAT()));
        if (saleDTO.priceReduction() > 0)
            System.out.println(String.format("Discount: \t\t\t\t-%.2f SEK", saleDTO.priceReduction()));
        float priceToPay = saleDTO.totalPrice() - saleDTO.priceReduction();
        System.out.println(String.format("Final: \t\t\t\t\t%.2f SEK", priceToPay));
        System.out.println(String.format("Cash: \t\t\t\t\t%.2f SEK", payment));
        System.out.println(String.format("Change: \t\t\t\t%.2f SEK", payment - priceToPay));

        System.out.println("------------------ End receipt ---------------------");

    }

}
