package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Entity
@Table(name = "timesheet_template")
@Data
@NoArgsConstructor
public class TimesheetTemplate {
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
    List<UserGroup> groups;

    @Expose
    @NonNull
    @Column()
    String location;

    @Expose
    @NonNull
    @Column(name = "display_name")
    String displayName;

    @Expose
    @Column()
    boolean display;

    @Expose
    @NonNull
    @Column(name = "modified_on")
    Timestamp modifiedOn;

    @Expose
    @NonNull
    @ManyToOne
    User modifiedBy;
}
