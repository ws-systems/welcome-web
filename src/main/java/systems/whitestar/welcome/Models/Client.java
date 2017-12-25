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
@Table(name = "clients")
@Data
@NoArgsConstructor
public class Client extends BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @ManyToOne
    Site site;

    @Expose
    @NonNull
    @Column(name = "first_name")
    String firstName;

    @Expose
    @NonNull
    @Column(name = "last_name")
    String lastName;

    @Expose
    @NonNull
    @Column()
    String email;

    @Expose
    @NonNull
    @Column(name = "subscription_status")
    EmailSubscriptionStatus subscriptionStatus;

    @Expose
    @Column(name = "external_type")
    String externalType;
}
