package nocast.storeservice.user.repository;

import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.user.dto.ReadUserDto;
import nocast.storeservice.user.mapper.ReadDtoMapper;
import nocast.storeservice.user.persistence.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor <User> {

Page<User> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);

}
