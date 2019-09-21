package org.openehr.rm.composition;

import org.openehr.rm.data_types.DV_DATE_TIME;
import org.openehr.rm.data_types.DV_TEXT;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://specifications.openehr.org/releases/RM/latest/ehr.html#_instruction_class">
 *     specifications.openehr.org</a>
 */
public class INSTRUCTION extends CARE_ENTRY {

    // --------------------------------------------------------------------------------------------------------------
    //  Attributes
    // --------------------------------------------------------------------------------------------------------------

    public DV_TEXT narrative() {
        throw new UnsupportedOperationException();
    }

    public Optional<DV_DATE_TIME> expiry_time() {
        throw new UnsupportedOperationException();
    }

    public Optional<List<ACTIVITY>> activities() {
        throw new UnsupportedOperationException();
    }

    // --------------------------------------------------------------------------------------------------------------
    //  Invariants
    // --------------------------------------------------------------------------------------------------------------

    // TODO Unit test
    public void Activities_valid() {
        if (activities().isPresent()) {
            if (activities().get().isEmpty()) {
                throw new IllegalStateException("activities /= Void implies not activities.is_empty");
            }
        }
    }
}
