package nocast.storeservice.category.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.CollectionType;

import java.time.Instant;
import java.util.Map;

/**
 * @author zaraza
 * @mail zaraza.yt@mail.ru
 */

@AllArgsConstructor
@Builder(toBuilder = true)
@Jacksonized
@Entity
@NoArgsConstructor
@Table(name = "category")
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

    @ElementCollection
    @CollectionTable(name = "category_info", joinColumns = @JoinColumn(name = "category_id"))
    @MapKeyColumn(name = "default_lang_code")
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



