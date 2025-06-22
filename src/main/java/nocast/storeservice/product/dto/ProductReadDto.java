package nocast.storeservice.product.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class ProductReadDto {

    Long id;
    String name;
    String description;
    BigDecimal price;
    PriceType priceType;
    int stockQuantity;
    int categoryId;
    Currency currency;
    String imageUrl;
    Integer sortOrder;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
