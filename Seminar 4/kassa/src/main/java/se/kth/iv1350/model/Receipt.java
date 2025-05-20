package se.kth.iv1350.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.kth.iv1350.DTO.ItemDTO;

/**
 * Prints an receipt of an @link Sale on an external printer
 */
public class Receipt {
    
    private Sale sale;

    /**
     * Constructor for @link Receipt
     */
    public Receipt(Sale sale){

        this.sale = sale;

    }

    /**
     * Prints the @link Receipt in the printer
     */
    public void print(){

        int saleLength = sale.getItemCount();
        System.out.println("------------------ Begin receipt -------------------");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println(String.format("Time of Sale: %s\n\n", time));

        for(int i = 0; i < saleLength; i++){

            Item item = sale.getItem(i);
            ItemDTO itemDTO = item.getItemDTO();
            System.out.println(String.format("%s\t%d x %.2f\t%.2f SEK", itemDTO.getName(), item.getAmount(), itemDTO.getTotalPrice(), itemDTO.getTotalPrice() * item.getAmount()));

        }
        
        System.out.println(String.format("\nTotal: \t\t\t\t\t%.2f SEK", sale.getTotalPrice()));
        System.out.println(String.format("VAT: %.2f", sale.getTotalVAT()));
        System.out.println(String.format("Discount: \t\t\t\t-%.2f SEK", sale.getPriceReduction()));
        System.out.println(String.format("Final: \t\t\t\t\t%.2f SEK", sale.getDiscountedPrice()));
        System.out.println(String.format("Cash: \t\t\t\t\t%.2f SEK", sale.getAmountPaid()));
        System.out.println(String.format("Change: \t\t\t\t%.2f SEK", sale.getAmountPaid() - sale.getTotalPrice()));

        System.out.println("------------------ End receipt ---------------------");

    }

}
