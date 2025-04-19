package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.dto.TreeViewOptions;
import nocast.storeservice.category.persistence.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Import(TestcontainersConfiguration.class)
@Rollback
@ApplicationModuleTest
@RequiredArgsConstructor
@Sql(scripts = "/test-category-data.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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
        Assertions.assertThat(page)
                .isNotEmpty()
                .hasSize(2);
        page.forEach(System.out::println);
    }


    //Поиск всех категорий по дефолтной ветке/пейджу/фильтру
    @Test
    void findAllTest_defaultPage() {
        Page<CategoryReadDto> page = categoryService.findAll();

        Assertions.assertThat(page).isNotNull();
        assertEquals(page.getTotalElements(), 3);
        assertEquals(page.getContent().get(0).getInfo().getName(), "Electronics");
    }

    //Поиск всех категорий по глубине ветки/дерева || EN
    @Test
    void findAll_TreeViewOptions() {
        final var tree = TreeViewOptions.builder().treeDepth(0).branchDepth(0).build();
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
        final var tree = TreeViewOptions.builder().treeDepth(0).branchDepth(0).build();
        final var filter = CategoryFilter.builder().name("Electronics").level(0).isLeaf(false).isRoot(true).parentId(null).build();

        Page<CategoryReadDto> page = categoryService.findAll(tree,Pageable.unpaged(Sort.by("sortOrder").ascending()),filter);

        Assertions.assertThat(page).isNotNull();
        assertEquals(page.getTotalElements(), 1);
        assertEquals(page.getContent().size(), 1);
        assertEquals(page.getContent().get(0).getInfo().getName(), filter.getName());
    }

    //Поиск категории - Электроника/Electronics || EN
    @Test
    void findById() {
        Optional<CategoryReadDto> result = categoryService.findById(1);

        Assertions.assertThat(result).isPresent();
        assertEquals(result.get().getId(), 1);
        assertEquals(result.get().getLevel(), 0);
        assertEquals(result.get().getInfo().getName(), "Electronics");
    }

    //Поиск категории - Художественная/Fiction по фильтрам level1 && parent_id
    @Test
    void findAll_Filters_Level1() {
        final var tree = TreeViewOptions.builder().treeDepth(0).branchDepth(0).build();
        final var filter = CategoryFilter.builder().name("Fiction").level(1).isLeaf(false).isRoot(false).parentId(3).build();

        Page<CategoryReadDto> page = categoryService.findAll(tree,Pageable.unpaged(Sort.by("sortOrder").ascending()),filter);

        Assertions.assertThat(page).isNotNull();
        assertEquals(page.getTotalElements(), 1);
        assertEquals(page.getContent().size(), 1);
        assertEquals(page.getContent().get(0)
                .getInfo().getName(), filter.getName());
        assertEquals(page.getContent().get(0)
                .getInfo().getDescription(), "Novels and stories");
    }

}
