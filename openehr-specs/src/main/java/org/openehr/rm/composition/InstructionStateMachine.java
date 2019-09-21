package org.openehr.rm.composition;

import com.google.common.collect.Lists;
import ksch.rm.ehr.Instruction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static org.openehr.rm.composition.InstructionStateMachine.Event.*;

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
                new Transition(Instruction.State.INITIAL, schedule, Instruction.State.SCHEDULED),
                new Transition(Instruction.State.INITIAL, initiate, Instruction.State.PLANNED),
                new Transition(Instruction.State.INITIAL, start, Instruction.State.ACTIVE)
        );
    }

    public IsmTransition process(Event careflowStep) {
        Optional<Transition> transition = transitions.stream()
                .filter(t -> t.getSourceState().equals(currentState) && t.getEvent().equals(careflowStep))
                .findFirst(); // TODO Figuring out the required transition can be moved into a separate utility method

        if (transition.isPresent()) {
            Transition t = transition.get();
            currentState = t.getTargetState();
            return new IsmTransition(currentState, t, careflowStep);
        } else {
            throw new IllegalStateException();
        }
    }

    @RequiredArgsConstructor
    @Getter
    public class IsmTransition {
        private final Instruction.State currentState;
        private final Transition transition;
        private final Event careflowStep;
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
