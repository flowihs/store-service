package nocast.storeservice.category.persistence;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class CategoryInfo {

    private String name;

    private String description;

    private String metaTitle;

    private String metaDescription;
}
