package nocast.storeservice.product.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ProductEditDto {
    String name;
    String description;
    BigDecimal price;
    String priceType;
    Integer stockQuantity;
    String image;
    Integer sortOrder;
    boolean active;
}
