package nocast.storeservice.user.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import nocast.storeservice.user.enums.TypeRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Table(name = "users")
@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeRole roles;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}
