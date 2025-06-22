package nocast.storeservice.product.mapper;

import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.product.dto.PriceType;
import nocast.storeservice.product.dto.ProductEditDto;
import nocast.storeservice.product.persistence.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductEditMapper implements Mapper<ProductEditDto, Product> {

    @Override
    public Product map(ProductEditDto object) {
        Product product = new Product();
        copy(object, product);
        return product;
    }

    @Override
    public Product map(ProductEditDto from, Product to) {
        copy(from, to);
        return to;
    }


    public void copy(ProductEditDto object, Product product) {
        if (object.getName() != null) product.setName(object.getName());
        if (object.getDescription() != null) product.setDescription(object.getDescription());
        if (object.getPrice() != null) product.setPrice(object.getPrice());
        if (object.getImage() != null) product.setImage(object.getImage());
        if (object.getPriceType() != null) product.setPriceType(PriceType.valueOf(object.getPriceType()));
        if (object.getStockQuantity() != null) product.setStockQuantity(object.getStockQuantity());
        if (object.getSortOrder() != null) product.setSortOrder(object.getSortOrder());
        if (object.isActive()) product.setActive(object.isActive());
    }
}
