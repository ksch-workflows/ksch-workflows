package ksch.medicalrecords.entry;

import java.util.Optional;

public interface IsmTransition {

    String getCurrentState();

    Optional<String> getTransition();
}
