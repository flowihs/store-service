package nocast.storeservice.user.repository;
import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import nocast.storeservice.user.persistence.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface  UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor <User> {

}
