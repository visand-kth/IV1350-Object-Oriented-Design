package se.kth.iv1350.DTO;

import java.util.List;

public record SaleDTO (List<InventoryDTO> items, float totalPrice, float totalVAT, float priceReduction, int customerID){

    public SaleDTO {
        items = List.copyOf(items);
    }
    
}
