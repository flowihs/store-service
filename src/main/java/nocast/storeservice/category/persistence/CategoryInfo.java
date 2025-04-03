package nocast.storeservice.category.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInfo {

    private String name;

    private String description;

    private String metaTitle;

    private String metaDescription;
}
