package nocast.storeservice.category.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("category")
public class Category {

    @Id
    @Column("id")
    private Integer id;

    @Column("slug")
    private String slug;

    @Column("parent_id")
    private Integer parent;

    @Column("sort_order")
    private Integer sortOrder = 0;

    @Column("level")
    private Integer level = 0;

    @Column("translations")
    private Map<String, CategoryInfo> translations;

    @Column("default_lang_code")
    private String defaultLangCode;

    @Column("name_default")
    private String nameDefault = null;

    @Column("description_default")
    private String descriptionDefault = null;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;
}



