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
@Table(name = "meetings")
@Data
@NoArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @ManyToOne
    Site site;

    @Expose
    @NonNull
    @Column(name = "extarnal_id")
    String externalId;

    @Expose
    @NonNull
    @Column()
    String action;

    @Expose
    @NonNull
    @Column()
    String parameters;

    @Expose
    @Column()
    boolean display;
}
