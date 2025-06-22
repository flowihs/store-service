package nocast.storeservice.product.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.product.dto.ProductFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ProductServiceTest {
    @Autowired
    private final ProductService productService;

    @BeforeEach
    void setUp() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
    }

    @Test
    void findByIdTest() {
        final Long id = 1L;
        final var result = productService.findById(id);
        assertThat(result)
                .isNotNull()
                .isNotEmpty();
        final var info = result.get();
        assertThat(info)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("name", "Smartphone");
    }

    @Test
    void findAllDefaultTest() {
        final var result = productService.findAll();
        assertThat(result)
                .hasSize(6)
                .anyMatch(product -> product.getName().equals("Laptop"))
                .anyMatch(product -> product.getId().equals(1L))
                .anyMatch(product -> product.getId().equals(2L))
                .anyMatch(product -> product.getName().equals("Smartphone"));
    }

    @Test
    void findAllPage() {
        final var result = productService.findAll(PageRequest.of(0, 10));
        assertThat(result)
                .hasSize(6)
                .anyMatch(product -> product.getId().equals(4L))
                .anyMatch(product -> product.getName().equals("Smartwatch"))
                .anyMatch(product -> product.getId().equals(5L))
                .anyMatch(product -> product.getName().equals("Smartphone"))
                .anyMatch(product -> product.getId().equals(6L));
    }

    @Test
    void findAllByFilter() {
        final var result = productService.findAll(PageRequest.of(0, 10), ProductFilter.builder().name("Laptop").build());
        assertThat(result)
                .hasSize(1)
                .allMatch(product -> product.getId().equals(2L))
                .allMatch(product -> product.getName().equals("Laptop"))
                .allMatch(product -> product.getDescription().equals("High-performance laptop for gaming and work"))
                .allMatch(product -> product.getPrice().equals(new BigDecimal("1299.99")));
    }
}
