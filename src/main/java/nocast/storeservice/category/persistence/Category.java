package nocast.storeservice.category.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.Map;

/**
 * @author zaraza
 * @mail zaraza.yt@mail.ru
 */

@Value
@Builder(toBuilder = true)
@Jacksonized
@Table("category")
public class Category {
    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "slug")
    String slug;

    @Column(name = "parent_id")
    Integer parent;

    @Column(name = "sort_order")
    @Builder.Default
    Integer sortOrder = 0;

    @Column(name = "level")
    @Builder.Default
    Integer level = 0;

    @Column(name = "translations")
    Map<String, CategoryInfo> translations;

    @Column(name = "default_lang_code")
    String defaultLangCode;

    @Column(name = "created_at")
    @Builder.Default
    Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    Instant updatedAt;
}



