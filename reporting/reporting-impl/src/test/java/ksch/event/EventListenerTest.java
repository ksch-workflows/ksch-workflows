/*
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
