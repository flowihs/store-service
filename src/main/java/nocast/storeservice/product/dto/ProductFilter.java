package nocast.storeservice.product.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class ProductFilter {

    String name;
    String description;
    BigDecimal minPrice;
    BigDecimal maxPrice;
    PriceType priceType;
    Integer categoryId;
    Currency currency;
    LocalDateTime beforeCreateAt;
    LocalDateTime afterCreateAt;
}
