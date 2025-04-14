package nocast.storeservice.category.dto;

import lombok.Value;
import nocast.storeservice.category.persistence.CategoryInfo;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Value
public class CategoryTreeDto {
    Integer id;
    Integer parentId;
    CategoryInfo info;
    Integer sortOrder;
    Integer level;
    String image;
    List<CategoryTreeDto> subcategories;
}
