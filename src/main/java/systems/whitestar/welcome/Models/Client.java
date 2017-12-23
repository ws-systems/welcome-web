package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
