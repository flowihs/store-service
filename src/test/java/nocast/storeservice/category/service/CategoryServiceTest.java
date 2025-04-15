package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@RequiredArgsConstructor
@Import(TestcontainersConfiguration.class)
@Rollback
@ApplicationModuleTest
public class CategoryServiceTest {

    @Autowired
    private final CategoryService categoryService;

    @Test
    void testService() {

    }


}
