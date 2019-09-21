package ksch.openehr.rm.ehr;

import org.junit.Test;
import ksch.instruction_state_machine.InstructionStateMachine;
import org.openehr.rm.composition.ISM_TRANSITION;

import static org.junit.Assert.assertEquals;
import static ksch.instruction_state_machine.InstructionStateMachine.Event.schedule;
import static ksch.instruction_state_machine.InstructionStateMachine.State.INITIAL;
import static ksch.instruction_state_machine.InstructionStateMachine.State.SCHEDULED;

public class InstructionStateMachineTest {

    /**
     * @see "https://specifications.openehr.org/releases/RM/latest/ehr.html#_the_standard_instruction_state_machine_ism"
     */
    @Test
    public void should_calculate_state_transition() {
        InstructionStateMachine instructionStateMachine = new InstructionStateMachine(INITIAL, null);

        ISM_TRANSITION transition = instructionStateMachine.process(schedule);

        assertEquals("SCHEDULED", transition.getCurrentState().getValue());
        assertEquals("ksch.instruction_state_machine.InstructionStateMachine$State",
                transition.getCurrentState().getDefiningCode().getCodeString());
        assertEquals("ksch", transition.getCurrentState().getDefiningCode().getTerminologyId().toString());
        assertEquals(SCHEDULED, instructionStateMachine.getCurrentState());
    }

    @Test
    public void should_publish_application_event_after_state_transition() {

    }
}
