package se.kth.iv1350.DTO;

import java.time.LocalDateTime;

/**
 * A sale record
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 * 
 * @param saleDTO  An saleDTO instance from a sale
 * @param dateTime The timestamp of the saleDTO instance
 */
public record RecordDTO(SaleDTO saleDTO, LocalDateTime dateTime) {}