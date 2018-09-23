package org.leanhis.patientmanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PatientCreatedEvent {

    private final Patient patient;
}
