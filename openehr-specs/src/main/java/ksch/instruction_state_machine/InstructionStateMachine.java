package ksch.instruction_state_machine;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openehr.rm.composition.INSTRUCTION;
import org.openehr.rm.composition.ISM_TRANSITION;

import java.util.List;
import java.util.Optional;

import static ksch.instruction_state_machine.InstructionStateMachine.Event.*;

/**
 * @see <a href="https://specifications.openehr.org/releases/RM/latest/ehr.html#_the_standard_instruction_state_machine_ism">
 *     specifications.openehr.org</a>
 */
public class InstructionStateMachine {

    @Getter
    private State currentState;

    private final InstructionStateMachineController controller;

    private List<TransitionDefinition> possibleTransitionDefinitions;

    public InstructionStateMachine(State initialState, InstructionStateMachineController controller) {
        this.currentState = initialState;
        this.controller = controller;

        this.possibleTransitionDefinitions = Lists.newArrayList(
                new TransitionDefinition(State.INITIAL, schedule, State.SCHEDULED),
                new TransitionDefinition(State.INITIAL, initiate, State.PLANNED),
                new TransitionDefinition(State.INITIAL, start, State.ACTIVE)
        );
    }

    public ISM_TRANSITION process(Event careflowStep) {
        Optional<TransitionDefinition> transition = possibleTransitionDefinitions.stream()
                .filter(t -> t.getSourceState().equals(currentState) && t.getEvent().equals(careflowStep))
                .findFirst();

        if (transition.isPresent()) {
            TransitionDefinition t = transition.get();
            currentState = t.getTargetState();

            controller.handleStateChanged(currentState);

            return new ISM_TRANSITION(new StateInfo(currentState));
        } else {
            throw new IllegalStateException();
        }
    }

    public enum Event {
        schedule,
        start,
        initiate,
        postpone
    }

    public interface InstructionStateMachineController {

        /**
         * Method which gets called when the state machine has changed its state.
         *
         * @param currentState
         */
        void handleStateChanged(State currentState);
    }

    /**
     * @see <a href="https://specifications.openehr.org/releases/RM/latest/ehr.html#_the_standard_instruction_state_machine_ism">specifications.openehr.org</a>
     * @see INSTRUCTION
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

    @Getter
    @RequiredArgsConstructor
    private class TransitionDefinition {
        private final State sourceState;
        private final Event event;
        private final State targetState;
    }
}
