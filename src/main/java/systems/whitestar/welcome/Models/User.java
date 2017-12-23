package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @Column()
    @ManyToOne
    UserGroup group;

    @Expose
    @NonNull
    @Column()
    @ManyToOne
    UserRole role;
}
