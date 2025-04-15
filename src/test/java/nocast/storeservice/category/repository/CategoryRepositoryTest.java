package nocast.storeservice.category.repository;

import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback
@ApplicationModuleTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    private String nameRu = "name-ru";

    private String nameEn = "name-en";

    private String descriptionRu = "description-ru";

    private String descriptionEn = "description-en";

    private Category entity1 = Category.builder()
            .translations(
                    Map.of("ru", new CategoryInfo(nameRu, descriptionRu, null, null),
                            "en", new CategoryInfo(nameEn, descriptionEn, null, null))
            )
            .build();

    @Test
    void correctWork() {
        assertThat(repository.count()).isEqualTo(0);
        final var saved = repository.save(entity1);
        System.out.println("Saved: " + saved);
        assertThat(saved).matches(it -> it.getId() != null);
        repository.deleteAll();
        assertThat(repository.count()).isEqualTo(0);
    }
}
