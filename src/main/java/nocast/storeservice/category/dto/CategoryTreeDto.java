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
    CategoryInfo info;
    Integer parentId;
    List<CategoryTreeDto> subcategories;
    Integer sortOrder;
    Integer level;
    boolean isRoot;
    boolean isLeaf;
    String image;
}
