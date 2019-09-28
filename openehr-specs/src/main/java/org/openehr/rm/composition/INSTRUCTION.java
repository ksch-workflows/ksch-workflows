package org.openehr.rm.composition;

import lombok.RequiredArgsConstructor;
import org.openehr.rm.data_types.DV_DATE_TIME;
import org.openehr.rm.data_types.DV_TEXT;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://specifications.openehr.org/releases/RM/latest/ehr.html#_instruction_class">
 *     specifications.openehr.org</a>
 */
@RequiredArgsConstructor
public class INSTRUCTION extends CARE_ENTRY {

    private DV_TEXT narrative;

    @Nullable
    private DV_DATE_TIME expiry_time;

    @Nullable
    private List<ACTIVITY> activities;

    // --------------------------------------------------------------------------------------------------------------
    //  Constructor
    // --------------------------------------------------------------------------------------------------------------

    public INSTRUCTION(DV_TEXT narrative, @Nullable DV_DATE_TIME expiry_time) {
        this.narrative = narrative;
        this.expiry_time = expiry_time;
    }

    // --------------------------------------------------------------------------------------------------------------
    //  Accessors
    // --------------------------------------------------------------------------------------------------------------

    public DV_TEXT narrative() {
        return narrative;
    }

    public Optional<DV_DATE_TIME> getExpiryTime() {
        return Optional.ofNullable(expiry_time);
    }

    public Optional<List<ACTIVITY>> getActivities() {
        return Optional.ofNullable(activities);
    }

    // --------------------------------------------------------------------------------------------------------------
    //  Custom operations
    // --------------------------------------------------------------------------------------------------------------

    public void addActivity(ACTIVITY activity) {
        if (activities == null) {
            activities
        }
    }

    // --------------------------------------------------------------------------------------------------------------
    //  Invariants
    // --------------------------------------------------------------------------------------------------------------

    public void checkActivitiesValid() {
        if (getActivities().isPresent()) {
            if (getActivities().get().isEmpty()) {
                throw new IllegalStateException("activities /= Void implies not activities.is_empty");
            }
        }
    }
}
