package nocast.storeservice.category.dto;

import lombok.Value;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Value
public class CategoryCreateDto {
    Integer parentId;
    List<Category> subcategories;
    Integer sortOrder;
    String image;
    boolean isActive;
    Map<String, CategoryInfo> translations;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
