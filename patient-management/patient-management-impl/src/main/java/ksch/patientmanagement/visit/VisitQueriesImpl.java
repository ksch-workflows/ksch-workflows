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

package ksch.patientmanagement.visit;

import ksch.patientmanagement.DatabaseRecordNotFoundException;
import ksch.patientmanagement.patient.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VisitQueriesImpl implements VisitQueries {

    private final VisitRepository visitRepository;

    @Override
    public Visit get(UUID visitId) {
        return visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Could not find Visit entity with ID " + visitId));
    }

    @Override
    public boolean isActive(Patient patient) {
        return visitRepository.findAllByPatientId(patient.getId())
                .stream()
                .anyMatch(this::hasStartAndNoEnd);
    }

    private boolean hasStartAndNoEnd(VisitEntity v) {
        return v.getTimeStart() != null && v.getTimeEnd() == null;
    }

    @Override
    public Optional<Visit> getActiveVisit(Patient patient) {
        return visitRepository.findAllByPatientId(patient.getId())
                .stream()
                .filter(this::hasStartAndNoEnd)
                .map(e -> (Visit) e)
                .findFirst();
    }

    @Override
    public List<Visit> getAllActiveOpdVisits() {
        return visitRepository.findAllActiveOptVisits().stream()
                .map(e -> (Visit) e)
                .collect(toList());
    }

    @Override
    public Patient getPatient(UUID visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new DatabaseRecordNotFoundException(visitId, VisitEntity.class));
        return visit.getPatient();
    }

    @Override
    public Optional<Visit> findByOpdNumber(String opdNumber) {
        return visitRepository.findByOpdNumber(opdNumber);
    }
}
