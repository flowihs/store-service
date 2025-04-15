package nocast.storeservice.category.repository;

import nocast.storeservice.category.persistence.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @EntityGraph(attributePaths = {"parent", "subcategories"})
    Optional<Category> findByIdAndActive(Integer id, boolean active);

    @EntityGraph(attributePaths = {"parent", "subcategories"})
    Page<Category> findAllByLevelAndActive(Integer level, boolean active, Pageable pageable);
}
