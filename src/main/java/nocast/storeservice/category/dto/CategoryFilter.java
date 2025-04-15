package nocast.storeservice.category.dto;

import lombok.Value;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Value
public class CategoryFilter {
    Integer level;
    Integer parentId;
    String name;
}
