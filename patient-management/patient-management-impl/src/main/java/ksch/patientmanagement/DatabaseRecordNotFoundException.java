package ksch.patientmanagement;

import java.util.UUID;

public class DatabaseRecordNotFoundException extends RuntimeException {

    public DatabaseRecordNotFoundException(UUID recordId, Class cls) {
        super(String.format("Could not find database record of type '%s' with ID '%s'.",
                cls.getName(), recordId.toString()));
    }
}
