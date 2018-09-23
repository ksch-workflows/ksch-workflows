package org.leanhis.report;

import org.leanhis.PatientReport;

import java.time.LocalDate;

public class PatientReportImpl implements PatientReport {

    @Override
    public int getNumberOfNewPatients(LocalDate from, LocalDate to) {
        return 0;
    }
}
