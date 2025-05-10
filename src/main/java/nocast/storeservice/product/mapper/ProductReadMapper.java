package nocast.storeservice.product.mapper;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.product.dto.ProductReadDto;
import nocast.storeservice.product.persistence.Product;
import org.springframework.stereotype.Component;
import nocast.storeservice.common.Mapper;

@Component
@RequiredArgsConstructor
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {

    @Override
    public ProductReadDto map(Product object) {
        return new ProductReadDto(
                object.getId(),
                object.getName(),
                object.getDescription(),
                object.getPrice(),
                object.getPriceType(),
                object.getStockQuantity(),
                object.getCategoryId(),
                object.getCurrency(),
                object.getImage(),
                object.getSortOrder(),
                object.getCreatedAt(),
                object.getUpdatedAt()
        );
    }

}
