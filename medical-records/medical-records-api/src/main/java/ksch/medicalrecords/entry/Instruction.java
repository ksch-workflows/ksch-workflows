package ksch.medicalrecords.entry;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Instruction extends CareEntry {

    String getNarrative();

    Optional<LocalDateTime> getExpiryTime();

    Optional<Activity> getActivities();
}
