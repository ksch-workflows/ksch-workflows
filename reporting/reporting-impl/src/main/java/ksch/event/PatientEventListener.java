package ksch.event;

import lombok.RequiredArgsConstructor;
import ksch.patientmanagement.patient.PatientCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PatientEventListener {

    private final EventRepository eventRepository;

    @EventListener
    public void handlePatientCreatedEvent(PatientCreatedEvent patientCreatedEvent) {
        EventEntity event = EventEntity.builder()
                .eventType(patientCreatedEvent.getClass().getName())
                .pointInTime(LocalDateTime.now())
                .build();
        eventRepository.save(event);
    }
}
