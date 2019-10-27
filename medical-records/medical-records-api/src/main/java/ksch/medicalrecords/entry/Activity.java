package ksch.medicalrecords.entry;

import java.util.Optional;

public interface Activity {

    Optional<String> getTiming();

    String getDescription();
}
