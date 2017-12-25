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
@Table(name = "emails")
@Data
@NoArgsConstructor
public class EmailRecord {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @Column
    Timestamp timestamp;

    @Expose
    @NonNull
    @Column(name = "recipient_type")
    EmailRecipientType recipientType;

    @Expose
    @Column()
    int recipient;

    @Expose
    @NonNull
    @Column(name = "message_type")
    EmailMessageType messageType;
}
