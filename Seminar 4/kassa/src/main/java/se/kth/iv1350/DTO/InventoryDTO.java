package se.kth.iv1350.DTO;

/**
 * An DTO for registering an @link ItemDTO and an count
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 * 
 * @param itemDTO The @link ItemDTO of the item to be logged
 * @param count   The amount of @link itemDTO in this record
 */
public record InventoryDTO(ItemDTO itemDTO, int count) {}