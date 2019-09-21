package org.openehr.rm.composition;

import lombok.RequiredArgsConstructor;
import org.openehr.rm.common.PATHABLE;
import org.openehr.rm.data_types.DV_CODED_TEXT;
import org.openehr.rm.data_types.DV_TEXT;

import java.util.Optional;

/**
 * @see <a href="https://specifications.openehr.org/releases/RM/latest/ehr.html#_ism_transition_class">specifications.openehr.org</a>
 */
@RequiredArgsConstructor
public class ISM_TRANSITION extends PATHABLE {

    private final DV_CODED_TEXT current_state;

    // --------------------------------------------------------------------------------------------------------------
    //  Attributes
    // --------------------------------------------------------------------------------------------------------------

    /**
     * @return The ISM current state. Coded by openEHR terminology group Instruction states.
     */
    public DV_CODED_TEXT getCurrentState() {
        return current_state;
    }

    /**
     * @return The ISM transition which occurred to arrive in the current_state. Coded by openEHR terminology group
     * Instruction transitions.
     */
    public Optional<DV_CODED_TEXT> getTransition() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return The step in the careflow process which occurred as part of generating this action, e.g. dispense ,
     * start_administration. This attribute represents the clinical label for the activity, as opposed to
     * current_state which represents the state machine (ISM) computable form. Defined in archetype.
     */
    public Optional<DV_CODED_TEXT> getCareflowStep() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return Optional possibility of adding one or more reasons for this careflow step having been taken.
     * Multiple reasons may occur in medication management for example.
     */
    public Optional<DV_TEXT> getReason() {
        throw new UnsupportedOperationException();
    }

    // --------------------------------------------------------------------------------------------------------------
    //  Invariants
    // --------------------------------------------------------------------------------------------------------------

    public void checkCurrentStateValid() {
        throw new UnsupportedOperationException();
    }

    public void checkTransitionValid() {
        throw new UnsupportedOperationException();
    }
}
