package org.leanhis;

import java.time.LocalDate;

public interface PatientReport {

    int getNumberOfNewPatients(LocalDate from, LocalDate to);
}
