package nocast.storeservice.category.dto;

import lombok.Value;
import nocast.storeservice.category.persistence.CategoryInfo;

/**
 * DTO для представления ветки категорий с информацией о родителе
 *
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

// Она идеальна
@Value
public class CategoryBranchDto {
    Integer id;
    CategoryBranchDto parent;
    CategoryInfo info;
    Integer sortOrder;
    Integer level;
    String image;
}
