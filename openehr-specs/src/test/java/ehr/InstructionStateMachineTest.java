package ksch.openehr.rm.ehr;

import org.junit.Test;

import static ksch.rm.ehr.Instruction.State.INITIAL;
import static ksch.rm.ehr.Instruction.State.SCHEDULED;
import static org.openehr.rm.composition.InstructionStateMachine.Event.schedule;
import static org.junit.Assert.assertEquals;

public class InstructionStateMachineTest {

    /**
     * @see "https://specifications.openehr.org/releases/RM/latest/ehr.html#_the_standard_instruction_state_machine_ism"
     */
    @Test
    public void should_calculate_state_transition() {
        InstructionStateMachine instructionStateMachine = new InstructionStateMachine(INITIAL, null);

        instructionStateMachine.process(schedule);

        assertEquals(SCHEDULED, instructionStateMachine.getCurrentState());
    }

    @Test
    public void should_publish_application_event_after_state_transition() {

    }
}
