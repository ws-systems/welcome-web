package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @ManyToOne
    Site site;

    @Expose
    @NonNull
    @Column()
    String name;

    @Expose
    @NonNull
    @Column()
    String email;

    @Expose
    @NonNull
    @Column(name = "login_id")
    String loginId;

    @Expose
    @NonNull
    @Column(name = "external_id")
    String externalId;

    @Expose
    @NonNull
    @ManyToOne
    UserGroup group;

    @Expose
    @NonNull
    @ManyToOne
    UserRole role;

    @Builder
    public User(Site site, String name, String email, String loginId, String externalId, UserGroup group, UserRole role) {
        this.site = site;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.externalId = externalId;
        this.group = group;
        this.role = role;
    }

    public static User merge(User existing, User updated) {
        // TODO

        return existing;
    }
}
