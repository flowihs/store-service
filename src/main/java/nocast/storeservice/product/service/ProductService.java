package nocast.storeservice.product.service;

import nocast.storeservice.product.dto.ProductFilter;
import nocast.storeservice.product.dto.ProductReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<ProductReadDto> findAll();

    Page<ProductReadDto> findAll (Pageable pageable);

    Page<ProductReadDto> findAll (Pageable pageable, ProductFilter filter);

    Optional<ProductReadDto> findById (Long id);

}
