package systems.whitestar.welcome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates the different types of users that can receive automatic emails from Welcome.
 *
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Getter
@AllArgsConstructor
public enum EmailRecipientType {
    STAFF(User.class),
    CLIENT(Client.class);

    Class recipientClass;
}
