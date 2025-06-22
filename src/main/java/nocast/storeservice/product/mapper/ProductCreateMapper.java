package nocast.storeservice.product.mapper;

import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.product.dto.Currency;
import nocast.storeservice.product.dto.PriceType;
import nocast.storeservice.product.dto.ProductCreateDto;
import nocast.storeservice.product.persistence.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateMapper implements Mapper<ProductCreateDto, Product> {
    @Override
    public Product map(ProductCreateDto object) {
        return Product.builder()
                .name(object.getName())
                .price(object.getPrice())
                .active(object.isActive())
                .image(object.getImage())
                .currency(Currency.valueOf(object.getCurrency()))
                .description(object.getDescription())
                .priceType(PriceType.valueOf(object.getPriceType()))
                .categoryId(object.getCategoryId())
                .stockQuantity(object.getStockQuantity())
                .sortOrder(object.getSortOrder())
                .build();
    }
}
