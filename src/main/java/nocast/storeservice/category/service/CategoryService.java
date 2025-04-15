package nocast.storeservice.category.service;

import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.dto.TreeViewOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

public interface CategoryService {

    Page<CategoryReadDto> findAll();

    Page<CategoryReadDto> findAll(TreeViewOptions options);

    Page<CategoryReadDto> findAll(TreeViewOptions options, Pageable pageable);

    Page<CategoryReadDto> findAll(TreeViewOptions options, Pageable pageable, CategoryFilter filter);

    Optional<CategoryReadDto> findById(Integer id);
}
