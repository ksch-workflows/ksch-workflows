package org.leanhis;

import org.leanhis.patientmanagement.PatientCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RegistrationStatisticsImpl implements RegistrationStatistics {

    @EventListener
    public void handlePatientCreated(PatientCreatedEvent patientCreatedEvent) {
        System.out.println("###### " + patientCreatedEvent.getPatient().getId());
    }

    @Override
    public void doJustAnything() {
        System.out.println("###### doing Something #########");
    }
}
