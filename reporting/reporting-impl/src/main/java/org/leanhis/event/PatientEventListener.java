package org.leanhis.event;

import lombok.RequiredArgsConstructor;
import org.leanhis.patientmanagement.PatientCreatedEvent;
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
