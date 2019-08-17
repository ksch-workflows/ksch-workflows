package ksch.openehr.rm.ehr;

import java.util.List;

/**
 * https://specifications.openehr.org/releases/RM/latest/ehr.html#_instruction_and_action
 */
public class Instruction {

    private State state;

    private List<Action> actions;

    /**
     * https://specifications.openehr.org/releases/RM/latest/ehr.html#_the_standard_instruction_state_machine_ism
     */
    public enum State {

        /**
         * "initial state, prior to planning activity"
         */
        INITIAL,

        /**
         * "the action has been described, but has not as yet been performed."
         */
        PLANNED,

        /**
         * "the action has not been performed and will not without specific conditions being met. Specifically events
         * and conditions that would normally 'activate' the instruction will be ignored, until a restore event occurs."
         */
        POSTPONED,

        /**
         * "the action will be performed at some designated future time, and has been booked in a scheduling system."
         */
        SCHEDULED,

        /**
         * "the action was defined, but was cancelled before being performed."
         */
        CANCELLED,

        /**
         * "the action is being performed according to its definition. The entire course of medication or therapy
         * corresponds to this state."
         */
        ACTIVE,

        /**
         * "the action was begun, but has been stopped temporarily, and will not be restarted until explicitly resumed."
         */
        SUSPENDED,

        /**
         * "the action began but was permanently terminated before normal completion."
         */
        ABORTED,

        /**
         * "the action began and was completed normally."
         */
        COMPLETED,

        /**
         * "the time during which the action could have been relevant has expired; the action may have completed, been
         * cancelled, or never occurred."
         */
        EXPIRED
    }
}
