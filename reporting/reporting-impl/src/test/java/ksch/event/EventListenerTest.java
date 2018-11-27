package ksch.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventListenerTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void should_store_application_event_history() {

        whenPatientCreated();
        whenPatientCreated();

        List<EventEntity> currentEvents = eventRepository.findEvents(
                PatientCreatedEvent.class,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1));

        assertEquals(2, currentEvents.size());
    }

    private void whenPatientCreated() {
        eventPublisher.publishEvent(new PatientCreatedEvent(new PatientAdapter()));
    }
}

class PatientAdapter implements Patient {

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getPatientNumber() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getNameFather() {
        return null;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return null;
    }

    @Override
    public Gender getGender() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }
}
