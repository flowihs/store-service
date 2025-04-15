package nocast.storeservice.category.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryBranchDto;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.dto.CategoryTreeDto;
import nocast.storeservice.category.persistence.Category;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@RequiredArgsConstructor
@Component
public class CategoryReadMapper {

    private final RecursiveMapper<Category, CategoryBranchDto> categoryBranchMapper;
    private final RecursiveMapper<Category, CategoryTreeDto> categoryTreeMapper;


    public CategoryReadDto map(@NonNull Category object, int branchDepth, int treeDepth) {
        final var langCode = LocaleContextHolder.getLocale().getLanguage();
        final var parent = branchDepth < 1
                ? null
                : Optional.ofNullable(object.getParent())
                .map(it -> categoryBranchMapper.map(it, branchDepth - 1))
                .orElse(null);
        final List<CategoryTreeDto> subcategories = treeDepth < 1
                ? Collections.emptyList()
                : Optional.ofNullable(object.getSubcategories())
                .filter(not(List::isEmpty))
                .orElseGet(Collections::emptyList)
                .stream()
                .map(it -> categoryTreeMapper.map(it, treeDepth - 1))
                .toList();
        return new CategoryReadDto(
                object.getId(),
                parent,
                subcategories,
                object.getTranslations().get(langCode),
                object.getSortOrder(),
                object.getLevel(),
                object.isRoot(),
                object.isLeaf(),
                object.getImage()
        );
    }
}
