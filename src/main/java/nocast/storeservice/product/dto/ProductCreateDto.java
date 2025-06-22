package nocast.storeservice.product.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductCreateDto {
    String name;
    String description;
    BigDecimal price;
    String priceType;
    Integer stockQuantity;
    Integer categoryId;
    String currency;
    String image;
    boolean active;
    Integer sortOrder;
}
