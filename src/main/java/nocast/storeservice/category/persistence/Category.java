package nocast.storeservice.category.persistence;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Value
@Builder(toBuilder = true)
@Jacksonized
@Table("category")
public class Category {
    @Id
    @Column("id")
    Integer id;

    @Column("slug")
    String slug;

    @Column("parent_id")
    Integer parent;

    @Column("sort_order")
    @Builder.Default
    Integer sortOrder = 0;

    @Column("level")
    @Builder.Default
    Integer level = 0;

    @Column("translations")
    Map<String, CategoryInfo> translations;

    @Column("default_lang_code")
    String defaultLangCode;

    @Column("created_at")
    @Builder.Default
    Instant createdAt = Instant.now();

    @Column("updated_at")
    Instant updatedAt;
}



