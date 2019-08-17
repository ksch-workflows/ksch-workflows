package ksch.openehr.rm.ehr;

import lombok.RequiredArgsConstructor;
import org.junit.Test;

import static ksch.openehr.rm.ehr.Instruction.State.*;
import static ksch.openehr.rm.ehr.InstructionStateMachine.Event.*;
import static ksch.openehr.rm.ehr.InstructionStateMachineTest.Given.given;
import static org.junit.Assert.assertEquals;

public class InstructionStateMachineTest {

    private InstructionStateMachine instructionStateMachine;

    /**
     * @see "https://specifications.openehr.org/releases/RM/latest/ehr.html#_the_standard_instruction_state_machine_ism"
     */
    @Test
    public void should_calculate_specified_state_transitions() {
        given(INITIAL).when(schedule).then(SCHEDULED);
        given(INITIAL).when(initiate).then(PLANNED);
        given(INITIAL).when(start).then(ACTIVE);

    }

    @Test
    public void should_publish_application_event_after_state_transition() {

    }

    static class Given {
        static When given(Instruction.State state) {
            return new When(new InstructionStateMachine(state, null));
        }
    }

    @RequiredArgsConstructor
    static class When {
        private final InstructionStateMachine instructionStateMachine;

        Then when(InstructionStateMachine.Event event) {
            instructionStateMachine.process(event);
            return new Then(instructionStateMachine.getCurrentState());
        }
    }

    @RequiredArgsConstructor
    static class Then {
        private final Instruction.State actualState;

        void then(Instruction.State expectedState) {
            assertEquals(expectedState, actualState);
        }
    }
}
