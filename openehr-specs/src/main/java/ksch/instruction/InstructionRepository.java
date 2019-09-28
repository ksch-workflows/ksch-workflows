package ksch.instruction;

import org.openehr.rm.composition.INSTRUCTION;

public interface InstructionRepository {

    INSTRUCTION save(INSTRUCTION instruction);
}
