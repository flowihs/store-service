package nocast.storeservice.product.repository;

import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import nocast.storeservice.product.persistence.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndActive(Long id, boolean isActive);

    @NonNull
    Page<Product> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);
}
