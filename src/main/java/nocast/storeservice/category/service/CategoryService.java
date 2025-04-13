package nocast.storeservice.category.service;

import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.dto.CategoryReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

public interface CategoryService {

    CategoryReadDto findAll();

    CategoryReadDto findById(Integer id);

    CategoryReadDto create(CategoryCreateDto category);

    CategoryReadDto updateById(Integer id, CategoryEditDto category);

    Void deleteById(Integer id);
}
