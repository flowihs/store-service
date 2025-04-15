package nocast.storeservice.category.service;

import nocast.storeservice.category.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

public interface CategoryService {

    Page<CategoryTreeDto> findAll();

    Page<CategoryTreeDto> findAll(Pageable pageable);

    Page<CategoryTreeDto> findAll(Pageable pageable, CategoryFilter filter);

    Optional<CategoryBranchDto> findById(Integer id);

}
