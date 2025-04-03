package nocast.storeservice.category.repository;

import nocast.storeservice.category.persistence.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Category, Integer> {
}
