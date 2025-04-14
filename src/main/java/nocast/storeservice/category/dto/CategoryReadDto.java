package nocast.storeservice.category.dto;

import lombok.Value;
import nocast.storeservice.category.persistence.CategoryInfo;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */


@Value
public class CategoryReadDto {

    Integer id;

    Integer parentId;

    CategoryInfo info;

    Integer sortOrder;

    Integer level;

    String image;

    Boolean isActive;
}
