package ksch.medicalrecords.entry;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Action extends CareEntry {

    LocalDateTime getTime();

    IsmTransition getIsmTransition();

    Optional<InstructionDetails> getInstructionDetails();

    String getDescription();
}
