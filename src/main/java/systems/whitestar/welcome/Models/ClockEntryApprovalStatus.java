package systems.whitestar.welcome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerate Clock (Timehseet) entry approval statuses.
 * Entries that were both opened and closed by Toggle Status should be automatically approved, while other
 * entries may or may not need manual approval.
 *
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@SuppressWarnings("unused")
@Getter
@AllArgsConstructor
public enum ClockEntryApprovalStatus {
    PENDING(false, false, "Pending Approval"),
    DECLINED(false, false, "Declined"),
    APPROVED_AUTOMATIC(true, false, "Automatically Approved"),
    APPROVED_MANUAL(true, true, "Approved");

    private boolean approved;
    private boolean approvalUserRequired;
    private String displayName;
}
