package nocast.storeservice.category.dto;

import lombok.Value;
import nocast.storeservice.category.persistence.CategoryInfo;

import java.util.Map;

@Value
public class CategoryCreateDto {
    Integer parentId;
    boolean isRoot;
    boolean isLeaf;
    Integer sortOrder;
    Integer level;
    String image;
    boolean active;
    String name;
    String description;
    String metaTitle;
    String metaDescription;
}
