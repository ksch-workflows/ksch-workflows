package org.leanhis;

import java.time.LocalDateTime;

public interface PatientReport {

    int getNumberOfNewPatients(LocalDateTime from, LocalDateTime to);
}
