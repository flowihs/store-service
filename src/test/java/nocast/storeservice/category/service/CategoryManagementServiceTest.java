package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@Rollback
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES)
@RequiredArgsConstructor
@Sql(scripts = "/test-category-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CategoryManagementServiceTest {
    @Autowired
    private final CategoryManagementService categoryService;
    @Autowired
    private final CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
    }

    @Test
    @Sql(scripts = "/test-category-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createCategoryTest() {
        final var langCode = LocaleContextHolder.getLocale();
        final var category = new CategoryCreateDto(1, true, false, 0, 0, "image.png", true, "testCategory", "Desc Category", "MetaTitleCategory", "MetaDescriptionCategory");
        categoryService.create(category);
        final var categories = categoryRepository.findAll();
        assertThat(categories)
                .anyMatch(it -> it.getParent() == null)
                .anyMatch(Category::isRoot)
                .anyMatch(it -> it.getImage().equals(category.getImage()));
        final var info = categories.getFirst();
        assertThat(info.getTranslations().get(langCode.toLanguageTag()))
                .hasFieldOrPropertyWithValue("name", category.getName())
                .hasFieldOrPropertyWithValue("description", category.getDescription());
    }

    @Test
    void updateCategoryTest() {
        final var edit = new CategoryEditDto(1, "image.jpg", 2, "Test1", "Desc1", "Title1", "MetaDesc1");
        categoryService.update(edit);
        final var result = categoryRepository.findById(1);
        assertThat(result)
                .isNotNull()
                .isNotEmpty();
                final var result1 = result.get();
        assertThat(result1)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("level", 0)
                .hasFieldOrPropertyWithValue("image", "image.jpg")
                .hasFieldOrPropertyWithValue("sortOrder", 2);
    }

    @Test
    void deleteCategoryTest() {
        final var deleted =  categoryService.delete(1);
        final var result = categoryRepository.findById(1);
        assertThat(deleted)
                .isTrue();
        assertThat(result)
                .isEmpty();
    }
}
