package nocast.storeservice.product.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.product.dto.ProductCreateDto;
import nocast.storeservice.product.dto.ProductEditDto;
import nocast.storeservice.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;


@Import(TestcontainersConfiguration.class)
@Rollback
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES)
@RequiredArgsConstructor
@Sql(scripts = "/test-product-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ProductManagementServiceTest {
    @Autowired
    private final ProductManagementService productManagement;
    @Autowired
    private final ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
    }

    @Test
    void createProductTest() {
        final var data = new ProductCreateDto("Test", "Test Description", BigDecimal.valueOf(14.99), "FREE", 4, 1, "USD", "image.png", true, 2);
        productManagement.create(data);
        final var result = productRepository.findById(7L);
        assertThat(result)
                .isPresent()
                .isNotEmpty()
                .isNotNull();
        final var product = result.get();
        assertThat(product)
                .hasFieldOrPropertyWithValue("name", "Test")
                .hasFieldOrPropertyWithValue("description", "Test Description")
                .hasFieldOrPropertyWithValue("price", BigDecimal.valueOf(14.99))
                .hasFieldOrPropertyWithValue("stockQuantity", 4);
    }

    @Test
    void editProductTest() {
        final var data = ProductEditDto.builder().name("Testing").description("Testing").active(true).build();
        productManagement.update(data, 3L, true);
        final var result = productRepository.findById(3L);
        assertThat(result)
                .isNotNull()
                .isNotEmpty();
        final var product = result.get();
        assertThat(product)
                .hasFieldOrPropertyWithValue("name", "Testing")
                .hasFieldOrPropertyWithValue("description", "Testing");
    }

    @Test
    void deleteProductTest() {
        final var data = 3L;
        final boolean result = productManagement.delete(data);
        assertThat(result)
                .isTrue();
    }
}
