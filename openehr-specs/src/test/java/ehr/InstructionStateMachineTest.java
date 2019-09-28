package ksch.openehr.rm.ehr;

import ehr.DummyEventPublisher;
import ehr.TestController;
import ksch.instruction.InstructionStateMachine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.openehr.rm.composition.ISM_TRANSITION;

import static ksch.instruction.InstructionStateMachine.Event.schedule;
import static ksch.instruction.InstructionStateMachine.State.INITIAL;
import static ksch.instruction.InstructionStateMachine.State.SCHEDULED;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InstructionStateMachineTest {

    @Spy
    private DummyEventPublisher dummyEventPublisher = new DummyEventPublisher();

    /**
     * @see "https://specifications.openehr.org/releases/RM/latest/ehr.html#_the_standard_instruction_state_machine_ism"
     */
    @Test
    public void should_calculate_state_transition() {
        InstructionStateMachine instructionStateMachine = new InstructionStateMachine(INITIAL, null);

        ISM_TRANSITION transition = instructionStateMachine.process(schedule);

        assertEquals("SCHEDULED", transition.getCurrentState().getValue());
        assertEquals("ksch.instruction.InstructionStateMachine$State",
                transition.getCurrentState().getDefiningCode().getCodeString());
        assertEquals("ksch", transition.getCurrentState().getDefiningCode().getTerminologyId().toString());
        assertEquals(SCHEDULED, instructionStateMachine.getCurrentState());
    }

    @Test
    public void should_publish_application_event_after_state_transition() {
        InstructionStateMachine instructionStateMachine = new InstructionStateMachine(
                INITIAL, new TestController(dummyEventPublisher)
        );

        ISM_TRANSITION transition = instructionStateMachine.process(schedule);

        verify(dummyEventPublisher).publishStateChangeEvent(SCHEDULED);
    }
}
