package ksch.instruction;

import lombok.RequiredArgsConstructor;
import org.openehr.rm.composition.INSTRUCTION;
import org.openehr.rm.data_types.DV_TEXT;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstructionCommands {

    private final InstructionRepository instructionRepository;

    public INSTRUCTION createInstruction(String narrative) {
        INSTRUCTION result = new INSTRUCTION(new DV_TEXT(narrative), null, null);
        return instructionRepository.save(result);
    }

    public void update(INSTRUCTION instruction) {
        instructionRepository.save(instruction);
    }
}
