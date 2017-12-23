package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Entity
@Table(name = "user_permissions")
@Data
@NoArgsConstructor
public class UserPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @Column()
    String key;

    @Expose
    @NonNull
    @Column(name = "display_name")
    String displayName;
}
