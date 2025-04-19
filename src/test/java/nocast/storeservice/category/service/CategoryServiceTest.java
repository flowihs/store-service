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
        final var options = new TreeViewOptions(2, 1);
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
        Page<CategoryReadDto> page = categoryService.findAll();

        assertThat(page).isNotNull();
        assertEquals(page.getTotalElements(), 3);
        assertEquals(page.getContent().get(0).getInfo().getName(), "Electronics");
    }

    //Поиск всех категорий по глубине ветки/дерева || EN
    @Test
    void findAll_TreeViewOptions() {
        final var tree = new TreeViewOptions(0, 0);
        Page<CategoryReadDto> page = categoryService.findAll(tree);

        assertEquals(page.getContent().size(), 3);
        assertEquals(page.getContent().get(0)
                .getInfo().getName(), "Electronics");

        assertEquals(page.getContent().get(1)
                .getInfo().getName(), "Clothing");

        assertEquals(page.getContent().get(2)
                .getInfo().getName(), "Books");
    }

    //Поиск и фильтрация категории - Электроника/Electronics || EN || level = 0
    @Test
    void findAll_Filters() {
        final var tree = new TreeViewOptions(0, 0);
        final var filter = CategoryFilter.builder().name("Electronics").level(0).isLeaf(false).isRoot(true).parentId(null).build();

        Page<CategoryReadDto> page = categoryService.findAll(tree, Pageable.unpaged(Sort.by("sortOrder").ascending()), filter);

        assertThat(page).isNotNull();
        assertEquals(page.getTotalElements(), 1);
        assertEquals(page.getContent().size(), 1);
        assertEquals(page.getContent().get(0).getInfo().getName(), filter.getName());
    }

    //Поиск категории - Электроника/Electronics || EN
    @Test
    void findById() {
        Optional<CategoryReadDto> result = categoryService.findById(1);

        assertThat(result).isPresent();
        assertEquals(result.get().getId(), 1);
        assertEquals(result.get().getLevel(), 0);
        assertEquals(result.get().getInfo().getName(), "Electronics");
    }

    //Поиск категории - Художественная/Fiction по фильтрам level1 && parent_id
    @Test
    void findAll_Filters_Level1() {
        final var tree = new TreeViewOptions(0, 0);
        final var filter = CategoryFilter.builder().name("Fiction").level(1).isLeaf(false).isRoot(false).parentId(3).build();

        Page<CategoryReadDto> page = categoryService.findAll(tree, Pageable.unpaged(Sort.by("sortOrder").ascending()), filter);

        assertThat(page).isNotNull();
        assertEquals(page.getTotalElements(), 1);
        assertEquals(page.getContent().size(), 1);
        assertEquals(page.getContent().get(0)
                .getInfo().getName(), filter.getName());
        assertEquals(page.getContent().get(0)
                .getInfo().getDescription(), "Novels and stories");
    }

}
