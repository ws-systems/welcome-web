package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
public class UserRole {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @ManyToOne
    Site site;

    @Expose
    @NonNull
    @OneToMany
    List<UserPermission> permissions;
}
