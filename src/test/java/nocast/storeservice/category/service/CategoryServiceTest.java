package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.dto.TreeViewOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@Rollback
@ApplicationModuleTest
@RequiredArgsConstructor
@Sql(scripts = "/test-category-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CategoryServiceTest {
    @Autowired
    private final CategoryService categoryService;

    @BeforeEach
    void setUp() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
    }

        @Test
        void simpleTest() {
            final var filter = CategoryFilter.builder().name("одежда").build();
            final var page = categoryService.findAll(
                    new TreeViewOptions(0, 0), Pageable.unpaged(), filter
            );
            assertThat(page)
                    .isNotEmpty()
                    .hasSize(2);
            page.forEach(System.out::println);
        }

    @Test
    void findAllWithOptions() {
        final var options = new TreeViewOptions(3, 1);
        final var actual = categoryService.findAll(options);
        assertThat(actual)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .allMatch(Objects::nonNull)
                .allMatch(it -> it.getParent() == null && it.isRoot());
        final var electronics = actual.get()
                .filter(it -> it.getInfo().getName().equals("Electronics"))
                .findAny();
        assertThat(electronics)
                .isNotEmpty()
                .get()
                .matches(it -> it.getSubcategories() != null
                                && !it.getSubcategories().isEmpty()
                                && it.getSubcategories().stream().allMatch(
                                categoryTree -> categoryTree.getSubcategories() != null
                                        && categoryTree.getSubcategories().isEmpty()
                        )
                );
    }

    //Поиск всех категорий по дефолтной ветке/пейджу/фильтру
    @Test
    void findAllTest_defaultPage() {
        final var actual = categoryService.findAll();
        actual.getContent().forEach(category -> System.out.println(category));
        assertThat(actual)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .allMatch(Objects::nonNull)
                .allMatch(it -> it.getParent() == null
                        && it.isRoot())
                .allMatch(it -> it.getSubcategories() != null)
                .allMatch(it -> it.getId() != null);
        final var electronics = actual.get()
                .filter(it -> it.getId().equals(1)
                        && it.getId().equals(2)
                        && it.getId().equals(3))
                .findAny();

    }

    //Поиск и фильтрация категории - Электроника/Electronics || EN || level = 0
    @Test
    void findAll_Filters_level0() {
        final var tree = new TreeViewOptions(0, 0);
        final var filter = CategoryFilter.builder().name("Electronics").level(0).isLeaf(false).isRoot(true).parentId(null).build();
        Pageable page = PageRequest.of(0, 10);
        final var actual = categoryService.findAll(tree, page, filter);
        assertThat(actual)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .allMatch(Objects::nonNull)
                .allMatch(it -> it.getParent() == null
                        && it.isRoot())
                .allMatch(it -> it.getSubcategories() != null)
                .allMatch(it -> it.getId() != null
                        && it.getId().equals(1))
                .allMatch(it -> it.getInfo()
                        .getName().equals("Electronics"));
    }

    //Поиск категории - Электроника/Electronics || EN
    @Test
    void findById() {
        final var actual = categoryService.findById(1);
        assertThat(actual)
                .isNotNull()
                .isNotEmpty();
        final var electronics = actual.get();
        assertThat(electronics)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("isRoot", true);
    }

    //Поиск категории - Художественная/Fiction по фильтрам level1 && parent_id || id=7
    @Test
    void findAll_Filters_Level1() {
        final var tree = new TreeViewOptions(2, 1);
        final var filter = CategoryFilter.builder().name("Fiction").level(1).isLeaf(false).isRoot(false).parentId(3).build();
        final var actual = categoryService.findAll(tree, Pageable.unpaged(Sort.by("sortOrder").ascending()), filter);

        assertThat(actual)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .allMatch(Objects::nonNull)
                .allMatch(it -> it.getParent() != null
                        && it.getParent().getId().equals(3))
                .allMatch(it -> it.getId() != null
                        && it.getId().equals(7))
                .allMatch(it -> it.getSubcategories() != null
                        && it.getSubcategories().get(0)
                        .getId().equals(11))
                .allMatch(it -> it.getLevel().equals(1));
    }

}
