package nocast.storeservice.category.mapper;

import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.persistence.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryEditMapper implements Mapper<CategoryEditDto, Category> {

    @Override
    public Category map(CategoryEditDto object) {
        Category category = new Category();
        copy(object, category);
        return category;
    }

    @Override
    public Category map(CategoryEditDto from, Category to) {
        copy(from, to);
        return to;
    }

    private void copy (CategoryEditDto from, Category to) {
        to.setSortOrder(from.getSortOrder());
        to.setImage(from.getImage());
        to.setTranslations(from.getTranslations());
    }
}
