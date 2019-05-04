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
package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.time.LocalDateTime.now;
import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;

@Service
@RequiredArgsConstructor
public class VisitTransactionsImpl implements VisitTransactions {

    private final VisitRepository visitRepository;

    private final PatientNumberGenerator patientNumberGenerator;

    @Override
    public Visit startVisit(Patient patient, VisitType visitType) {
        VisitEntity visit = VisitEntity.builder()
                .opdNumber(patientNumberGenerator.generateOpdNumber())
                .timeStart(now())
                .type(visitType)
                .patient(toPatientEntity(patient))
                .build();
        return visitRepository.save(visit);
    }

    @Override
    public Visit discharge(Patient patient) {
        VisitEntity visit = visitRepository.findAllByPatientId(patient.getId())
                .stream()
                .filter(this::hasStartAndNoEnd)
                .findFirst()
                .orElseThrow(() -> new NoActiveVisitException(patient.getId()));

        visit.setTimeEnd(now());
        return visitRepository.save(visit);
    }

    private boolean hasStartAndNoEnd(VisitEntity v) {
        return v.getTimeStart() != null && v.getTimeEnd() == null;
    }

    class NoActiveVisitException extends RuntimeException {
        public NoActiveVisitException(UUID patientId) {
            super("There is no active visit for patient " + patientId);
        }
    }
}
