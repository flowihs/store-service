package nocast.storeservice.category.dto;

import lombok.Builder;
import lombok.Value;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Value
@Builder
public class CategoryFilter {
    Integer level;
    Boolean isRoot;
    Boolean isLeaf;
    Integer parentId;
    String name;
    Boolean isActive = true;
}
