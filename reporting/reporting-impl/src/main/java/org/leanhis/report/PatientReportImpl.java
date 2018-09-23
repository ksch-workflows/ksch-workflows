package org.leanhis.report;

import lombok.RequiredArgsConstructor;
import org.leanhis.PatientReport;
import org.leanhis.event.EventRepository;
import org.leanhis.patientmanagement.PatientCreatedEvent;
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
