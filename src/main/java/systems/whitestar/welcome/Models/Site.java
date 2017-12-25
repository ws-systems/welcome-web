package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Entity
@Table(
        name = "sites",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id"),
                @UniqueConstraint(columnNames = "subdomain")
        }
)
@Data
@NoArgsConstructor
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @Column(name = "display_name")
    String displayName;

    @Expose
    @NonNull
    @Column
    String subdomain;

    @Expose
    @OneToOne
    SiteAsset assets;
}
