package nocast.storeservice.category.repository;

import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
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

    @Autowired
    private TransactionalOperator transactionalOperator;

    private String nameRu = "name-ru";

    private String nameEn = "name-en";

    private String descriptionRu = "description-ru";

    private String descriptionEn = "description-en";

    private Category entity1 = Category.builder()
            .slug("dummy-slug")
            .translations(
                    Map.of("ru", new CategoryInfo(nameRu, descriptionRu, null, null),
                            "en", new CategoryInfo(nameEn, descriptionEn, null, null))
            )
            .defaultLangCode("ru")
            .build();


    @Test
    void testConnectionToDb() {
        StepVerifier.create(repository.count())
                .expectNextMatches(count -> count >= 0)
                .verifyComplete();
    }

    @Test
    void correctSave() {
        Mono<Category> operation = repository.save(entity1)
                .flatMap(saved -> repository.findById(saved.getId()))
                .as(transactionalOperator::transactional);

        StepVerifier.create(operation)
                .expectNextMatches(saved -> {
                    System.out.println("Saved and retrieved: " + saved);
                    return saved.getId() != null
                            && saved.getSlug().equals(entity1.getSlug());
                })
                .verifyComplete();
    }

    @Test
    void correctUpdate() {
        Mono<Category> operation = repository.save(entity1)
                .map(saved -> saved.toBuilder()
                        .slug("updated-slug")
                        .defaultLangCode("en")
                        .updatedAt(Instant.now())
                        .build())
                .flatMap(repository::save)
                .as(transactionalOperator::transactional);

        StepVerifier.create(operation)
                .expectNextMatches(updated -> {
                    System.out.println("Updated entity: " + updated);
                    return updated.getId() != null
                            && updated.getSlug().equals("updated-slug")
                            && updated.getDefaultLangCode().equals("en");
                })
                .verifyComplete();
    }
}
