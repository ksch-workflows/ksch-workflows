package ksch.report;

import ksch.event.EventRepository;
import lombok.RequiredArgsConstructor;
import ksch.PatientReport;
import ksch.patientmanagement.patient.PatientCreatedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PatientReportImpl implements PatientReport {

    private final EventRepository eventRepository;

    @Override
    public int getNumberOfNewPatients(LocalDateTime from, LocalDateTime to) {
        return eventRepository.findEvents(PatientCreatedEvent.class, from, to).size();
    }
}
