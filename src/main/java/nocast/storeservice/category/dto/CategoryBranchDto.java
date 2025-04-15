package nocast.storeservice.category.dto;

import lombok.Value;
import nocast.storeservice.category.persistence.CategoryInfo;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Value
public class CategoryBranchDto {
    Integer id;
    CategoryBranchDto parent;
    CategoryInfo info;
    Integer sortOrder;
    Integer level;
    boolean isRoot;
    boolean isLeaf;
    String image;
}
