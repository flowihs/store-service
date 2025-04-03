package nocast.storeservice.category.service;

import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.dto.CategoryReadDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

public interface CategoryService {

    Flux<CategoryReadDto> findAll();

    Mono<CategoryReadDto> findById(Integer id);

    Mono<CategoryReadDto> create(CategoryCreateDto category);

    Mono<CategoryReadDto> updateById(Integer id, CategoryEditDto category);

    Mono<Void> deleteById(Integer id);
}
