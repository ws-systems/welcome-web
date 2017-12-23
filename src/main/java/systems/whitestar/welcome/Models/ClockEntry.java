package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
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
@Table(name = "clock")
@Data
@NoArgsConstructor
public class ClockEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @Column()
    @ManyToOne
    User user;

    @Expose
    @NonNull
    @Column(name = "time_in")
    Timestamp timeIn;

    @Expose
    @NonNull
    @Column(name = "time_out")
    Timestamp timeOut;

    @Expose
    @NonNull
    @Column(name = "approval_status")
    ClockEntryApprovalStatus clockEntryApprovalStatus;

    @Expose
    @NonNull
    @Column(name = "approval_user")
    @ManyToOne
    User approvalUser;
}