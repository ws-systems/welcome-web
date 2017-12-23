package systems.whitestar.welcome.Models;

import lombok.AllArgsConstructor;

/**
 * Email Subscription status for users, to determine what class of emails they should and should not be receiving.
 *
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@SuppressWarnings("unused")
@AllArgsConstructor
public enum EmailSubscriptionStatus {
    UNSUBSCRIBED(false, false),
    SUBSCRIBED_NOTIFICATION(false, true),
    SUBSCRIBED_ALL(true, true);

    private boolean sendGeneralEmails;
    private boolean sendNotificationEmails;
}
