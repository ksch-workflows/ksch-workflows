package ksch.instruction;

import org.openehr.base.base_types.TERMINOLOGY_ID;
import org.openehr.rm.data_types.CODE_PHRASE;
import org.openehr.rm.data_types.DV_CODED_TEXT;

class StateInfo extends DV_CODED_TEXT {

    public StateInfo(InstructionStateMachine.State state) {
        // TODO Consider simplification instead of bogus terminology data
        super(new CODE_PHRASE(new TERMINOLOGY_ID("ksch"), InstructionStateMachine.State.class.getName()), state.toString());
    }
}
