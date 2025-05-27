package se.kth.iv1350.DTO;

import java.time.LocalDateTime;

public record RecordDTO (SaleDTO saleDTO, LocalDateTime dateTime){}
