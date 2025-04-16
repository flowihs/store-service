package nocast.storeservice.category.repository;

import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import nocast.storeservice.category.persistence.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, QuerydslPredicateExecutor<Category> {

    @EntityGraph(attributePaths = {"parent", "subcategories"})
    Optional<Category> findByIdAndActive(Integer id, boolean isActive);

    @NonNull
    @EntityGraph(attributePaths = {"parent", "subcategories"})
    Page<Category> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);
}
