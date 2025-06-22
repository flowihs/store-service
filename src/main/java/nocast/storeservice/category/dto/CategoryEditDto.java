package nocast.storeservice.category.dto;

import lombok.Value;

@Value
public class CategoryEditDto {
    Integer id;
    String image;
    Integer sortOrder;
    String name;
    String description;
    String metaTitle;
    String metaDescription;
}
