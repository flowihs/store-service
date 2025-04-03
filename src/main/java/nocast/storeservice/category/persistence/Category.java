package nocast.storeservice.category.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("category")
public class Category {

    @Id
    @Column("id")
    private Integer id;

    @Column("slug")
    private String slug;

    @Column("parent_id")
    private Integer parent;

    @MappedCollection(idColumn = "parent_id")
    private List<Category> children;

    @Column("sort_order")
    private Integer sortOrder = 0;

    @Column("level")
    private Integer level = 0;

    @Column("translations")
    private Map<String, CategoryInfo> translations;

    @Column("default_language_code")
    private String defaultLanguageCode;

    @Column("name_default")
    private String nameDefault = null;

    @Column("description-default")
    private String descriptionDefault = null;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;
}



