package nocast.storeservice.product.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.product.dto.ProductCreateDto;
import nocast.storeservice.product.dto.ProductEditDto;
import nocast.storeservice.product.dto.ProductReadDto;
import nocast.storeservice.product.persistence.Product;
import nocast.storeservice.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {
    private final ProductRepository productRepository;
    private final Mapper<ProductCreateDto, Product> productMapper;
    private final Mapper<Product, ProductReadDto> productReadMapper;
    private final Mapper<ProductEditDto, Product> productEditMapper;

    @Override
    public Optional<ProductReadDto> create(ProductCreateDto dto) {
        return  Optional.ofNullable(dto)
                .map(productMapper::map)
                .map(productRepository::saveAndFlush)
                .map(productReadMapper::map);
    }

    @Override
    public Optional<ProductReadDto> update(ProductEditDto dto, Long id, boolean active) {
        return Optional.ofNullable(dto)
                .map(it -> productRepository.findByIdAndActive(id, it.isActive()))
                .map(it -> productEditMapper.map(dto, it.get()))
                .map(productRepository::saveAndFlush)
                .map(productReadMapper::map);
    }

    @Override
    public boolean delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
