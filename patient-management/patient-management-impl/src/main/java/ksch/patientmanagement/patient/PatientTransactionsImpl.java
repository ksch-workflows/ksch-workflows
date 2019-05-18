/**
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

package ksch.patientmanagement.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;

@Service
@RequiredArgsConstructor
public class PatientTransactionsImpl implements PatientTransactions {

    private final PatientRepository patientRepository;

    private final PatientNumberGenerator patientNumberGenerator;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Patient create(Patient patient) {
        PatientEntity patientEntity = toPatientEntity(patient);
        if (patient.getPatientNumber() == null) {
            patientEntity.setPatientNumber(patientNumberGenerator.generateOpdNumber());
        }
        patientEntity = patientRepository.save(patientEntity);
        eventPublisher.publishEvent(new PatientCreatedEvent(patientEntity));
        return patientEntity;
    }

    @Override
    public void update(Patient patient) {
        patientRepository.save(toPatientEntity(patient));
    }
}
