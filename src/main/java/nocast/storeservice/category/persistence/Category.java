package nocast.storeservice.category.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer sortOrder = 0;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer level = 0;

    @Column(columnDefinition = "jsonb NOT NULL")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, CategoryInfo> translations;

    @Column(nullable = false, length = 2)
    private String defaultLanguageCode;

    private String nameDefault = null;

    private String descriptionDefault = null;

    @Column(nullable = false)
    private Instant createdAt;

    private Instant updatedAt;
}



