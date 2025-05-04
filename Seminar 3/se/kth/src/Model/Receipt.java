package se.kth.src.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
    private Sale sale;
    private Payment payment;
    private Date time;
    public Receipt(Sale sale, Payment payment) {
        this.sale = sale;
        this.payment = payment;
        this.time = new Date();
    }
    public void print() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.printf("Time of Sale: %s\n\n", formatter.format(this.time));

        // Print each unique item and its count
        for (int i = 0; i < sale.items.length; i++) {
            
            if(sale.items[i].equals(null)) {
                break;
            }
            Item item = sale.items[i];
            int quantity = sale.count[i];
            double totalPrice = item.price * quantity;

            System.out.printf(
                "%-20s %d x %.2f SEK %.2f SEK\n",
                item.description.substring(0, Math.min(20, item.description.length())),
                quantity,
                item.price,
                totalPrice
            );
        }

        // Print total
        System.out.printf("\n%-20s %.2f SEK\n", "Total:", sale.runningTotal);

        // Print VAT (assumes uniform VAT per item)
        System.out.printf("%-20s %.2f SEK\n", "Total VAT:", sale.items[0].vat);

        // Print payment details
        System.out.printf("%-20s %.2f SEK\n", "Cash:", payment.cashPaid);
        System.out.printf("%-20s %.2f SEK\n", "Change:", payment.cashPaid - sale.runningTotal);
    }  
}
