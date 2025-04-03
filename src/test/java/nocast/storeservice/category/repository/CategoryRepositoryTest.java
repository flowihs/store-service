package nocast.storeservice.category.repository;

import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import reactor.test.StepVerifier;

import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Import(TestcontainersConfiguration.class)
@ApplicationModuleTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;


    @Test
    void testConnectionToDb() {
        StepVerifier.create(repository.count())
                .expectNextMatches(count -> count >= 0)
                .verifyComplete();
    }

    @Test
    void correctSave() {
        final var nameRu = "name-ru";
        final var descriptionRu = "description-ru";
        final var entity = Category.builder()
                .slug("dummy-slug")
                .translations(
                        Map.of("ru", new CategoryInfo(nameRu, descriptionRu, null, null),
                                "en", new CategoryInfo("name-en", "description-en", null, null))
                )
                .defaultLangCode("ru")
                .build();
        final var blocked = repository.save(entity).block();
        System.out.println(blocked);
        StepVerifier.create(repository.findById(blocked.getId()))
                .expectNextMatches(it -> {
                            System.out.println(it);
                            return it.getId() != null
                                    && it.getNameDefault().equals(nameRu)
                                    && it.getDescriptionDefault().equals(descriptionRu);
                        }
                )
                .verifyComplete();
    }
}
