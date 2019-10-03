package ksch.laboratory;

import java.util.List;
import java.util.UUID;

public interface LabCommands {
    LabOrder requestExamination(UUID opdNumber, List<LabOrderCode> tests);
}
