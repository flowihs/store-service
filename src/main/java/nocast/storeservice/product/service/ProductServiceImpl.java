package nocast.storeservice.product.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.product.dto.ProductFilter;
import nocast.storeservice.product.dto.ProductReadDto;
import nocast.storeservice.product.persistence.Product;
import nocast.storeservice.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static nocast.storeservice.product.persistence.Product.Fields.id;
import static nocast.storeservice.product.persistence.Product.Fields.sortOrder;
import static org.springframework.data.domain.Sort.by;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Mapper<Product, ProductReadDto> productReadMapper;
    private final Mapper<ProductFilter, Predicate> productPredicateMapper;
    private final Sort defaultSort = by(sortOrder, id);
    private final Pageable defaultPageable = PageRequest.of(0, 20, defaultSort);

    @Override
    public Page<ProductReadDto> findAll() {
        return this.findAll(defaultPageable);
    }

    @Override
    public Page<ProductReadDto> findAll(Pageable pageable) {
        ProductFilter filter = ProductFilter.builder().build();
        return this.findAll(pageable, filter);
    }

    @Override
    public Page<ProductReadDto> findAll(Pageable pageable, ProductFilter filter) {
        return productRepository.findAll(productPredicateMapper.map(filter), resolvePageable(pageable))
                .map(productReadMapper::map);
    }

    @Override
    public Optional<ProductReadDto> findById(Long id) {
        return productRepository.findByIdAndActive(id, true)
                .map(productReadMapper::map);
    }

    private Pageable resolvePageable(Pageable pageable) {
        if (pageable == null || pageable.isUnpaged()) {
            return defaultPageable;
        }
        Sort sort = pageable.getSort().isUnsorted()
                ? defaultSort
                : pageable.getSort();
        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );
    }
}
