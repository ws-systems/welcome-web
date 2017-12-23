package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
class BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @ManyToOne
    Site site;
}
