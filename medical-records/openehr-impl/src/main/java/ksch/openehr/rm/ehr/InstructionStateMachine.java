package ksch.openehr.rm.ehr;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ksch.openehr.rm.ehr.Instruction.State.*;
import static ksch.openehr.rm.ehr.InstructionStateMachine.Event.*;

/**
 * https://specifications.openehr.org/releases/RM/latest/ehr.html#_the_standard_instruction_state_machine_ism
 */
public class InstructionStateMachine {

    @Getter
    private Instruction.State currentState;

    private final InstructionStateMachineController controller;

    private List<Transition> transitions;

    public InstructionStateMachine(Instruction.State initialState, InstructionStateMachineController controller) {
        this.currentState = initialState;
        this.controller = controller;

        this.transitions = Lists.newArrayList(
                new Transition(INITIAL, schedule, SCHEDULED),
                new Transition(INITIAL, initiate, PLANNED),
                new Transition(INITIAL, start, ACTIVE)
        );
    }

    public Instruction.State process(Event event) {
        transitions.stream()
                .filter(t -> t.getSourceState().equals(currentState) && t.getEvent().equals(event))
                .findFirst()
                .map(t -> currentState = t.getTargetState());
        return currentState;
    }

    enum Event {
        schedule,
        start,
        initiate,
        postpone
    }

    @Getter
    @RequiredArgsConstructor
    class Transition {
        private final Instruction.State sourceState;
        private final Event event;
        private final Instruction.State targetState;
    }

    interface InstructionStateMachineController {

        void activated(Instruction.State previousState);
    }
}
