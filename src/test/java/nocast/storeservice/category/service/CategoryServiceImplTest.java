package nocast.storeservice.category.service;

import nocast.storeservice.category.dto.CategoryBranchDto;
import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.mapper.Mapper;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import nocast.storeservice.category.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ApplicationModuleTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

//    @Test
//    @Sql(scripts = "/category.sql")
//    void deleteByIdTest_Exists() {
//        int id = 4;
//        assertTrue(categoryRepository.existsById(id));
//
//        boolean result = categoryService.deleteById(id);
//        assertTrue(result);
//    }

//    @Test
//    @Sql(scripts = "/category.sql")
//      void deleteByIdTest_NotExists() {
//        int id = 3;
//        assertFalse(categoryRepository.existsById(id));
//
//        boolean result = categoryService.deleteById(id);
//        assertFalse(result);
//        categoryRepository.deleteById(4);
//    }

//        @Sql(scripts = "/category_update.sql")
//        @Test
//        void updateByIdTest_Success() {
//            int id = 1;
//            Map<String, CategoryInfo> map = new HashMap<>();
//            map.put("ru",new CategoryInfo("имя","описание","заголовок","мета-описание"));
//            CategoryEditDto categoryEditDto = new CategoryEditDto(5,"test-img.png", map);
//            Optional<CategoryBranchDto> updatedCategory =  categoryService.updateById(id, categoryEditDto);
//
//            assertEquals(updatedCategory.get().getImage(), "test-img.png");
//            assertEquals(updatedCategory.get().getSortOrder(), 5);
//        }

    @Sql(scripts = "/category.sql")
    @Test
    void findByIdTest_Exists() {
        int id = 4;
        Optional<CategoryBranchDto> categoryBranchDto = categoryService.findById(id);
        assertTrue(categoryBranchDto.isPresent());
        assertEquals(categoryBranchDto.get().getImage(), "image.png");
        categoryService.deleteById(id);
    }

    @Sql(scripts = "/category.sql")
    @Test
    void findByIdTest_NotExists() {
        int id = 7;
        Optional<CategoryBranchDto> categoryBranchDto = categoryService.findById(id);
        assertTrue(categoryBranchDto.isEmpty());
    }
}

