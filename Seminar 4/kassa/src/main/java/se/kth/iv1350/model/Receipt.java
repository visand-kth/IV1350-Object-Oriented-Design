package se.kth.iv1350.model;

import java.time.LocalDateTime;

/**
 * Represents a receipt for a Sale.
 */
public class Receipt {
    private final Sale sale;
    private final LocalDateTime dateTime;
    private float discount = 0f;

    /**
     * Creates a receipt for the given sale.
     * @param sale The sale to create a receipt for.
     */
    public Receipt(Sale sale) {
        this.sale = sale;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Sets the discount for this receipt.
     * @param discount The discount as a float (e.g., 0.1 for 10% off).
     */
    public void setDiscount(float discount) {
        if (discount < 0f || discount > 1f) {
            throw new IllegalArgumentException("Discount must be between 0 and 1.");
        }
        this.discount = discount;
    }

    /**
     * Creates a string for a receipt.
     * @return the receipt as a String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- RECEIPT -----\n");
        sb.append("Date: ").append(dateTime).append("\n");
        sb.append("Items:\n");
        for (Item item : sale.getItems()) {
            sb.append(item.getItemDTO().getName())
              .append(" x").append(item.getAmount())
              .append(" - ").append(item.getItemDTO().getPrice())
              .append(" each\n");
        }
        sb.append("-------------------\n");
        sb.append("Total: ").append(sale.getTotalPrice()).append("\n");
        sb.append("VAT: ").append(sale.getTotalVAT()).append("\n");
        if (discount > 0f) {
            float discountAmount = sale.getTotalPrice() * (1 - discount);
            sb.append("Discount: -").append(discountAmount).append(" (").append((int)(discount * 100)).append("%)\n");
            sb.append("Total after discount: ").append(sale.getTotalPrice() - discountAmount).append("\n");
        }
        sb.append("-------------------\n");
        return sb.toString();
    }
}
