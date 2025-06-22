package nocast.storeservice.product.service;

import nocast.storeservice.product.dto.ProductCreateDto;
import nocast.storeservice.product.dto.ProductEditDto;
import nocast.storeservice.product.dto.ProductReadDto;

import java.util.Optional;

public interface ProductManagementService {
    Optional<ProductReadDto> create(ProductCreateDto dto);

    Optional<ProductReadDto> update(ProductEditDto dto, Long id, boolean active);

    boolean delete(Long id);
}
