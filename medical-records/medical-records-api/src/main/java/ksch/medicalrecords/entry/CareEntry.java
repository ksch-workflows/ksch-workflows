package ksch.medicalrecords.entry;

import java.util.Optional;

public interface CareEntry extends Entry {

    Optional<String> getProtocol();
}
