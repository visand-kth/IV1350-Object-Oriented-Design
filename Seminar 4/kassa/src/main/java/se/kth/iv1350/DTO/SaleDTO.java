package se.kth.iv1350.DTO;

import java.util.List;

/**
 * A DTO snapshot of a sale
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 * 
 * @param items          A list of item records in the sale
 * @param totalPrice     The total price this sale costs
 * @param totalVAT       The accumulated vat calculated from all items in the
 *                       sale
 * @param priceReduction The total price reduced from discounts applied to this
 *                       sale
 * @param customerID     The unique identifier of the customer that holds this
 *                       sale (unless specified will be default 0)
 */
public record SaleDTO(List<InventoryDTO> items, float totalPrice, float totalVAT, float priceReduction, int customerID) {

    /**
     * Additional code to prevent editing of the list items under execution
     */
    public SaleDTO {
        items = List.copyOf(items);
    }

}
