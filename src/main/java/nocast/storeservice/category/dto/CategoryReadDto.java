package nocast.storeservice.category.dto;

import lombok.Value;
import nocast.storeservice.category.persistence.CategoryInfo;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Value
public class CategoryReadDto {
    Integer id;
    CategoryBranchDto parent;
    List<CategoryTreeDto> subcategories;
    CategoryInfo info;
    Integer sortOrder;
    Integer level;
    boolean isRoot;
    boolean isLeaf;
    String image;
}
