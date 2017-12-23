package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @Column()
    @ManyToOne
    Site site;

    @Expose
    @NonNull
    @Column()
    @ManyToOne
    Client client;

    @Expose
    @NonNull
    @Column(name = "time")
    Timestamp timestamp;

    @Expose
    @NonNull
    @Column()
    String action;

    @Expose
    @NonNull
    @Column()
    String parameters;
}
