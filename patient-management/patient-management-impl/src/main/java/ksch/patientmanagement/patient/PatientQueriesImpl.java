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

package ksch.patientmanagement.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientQueriesImpl implements PatientQueries {

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> findByNameOrNumber(String nameOrNumber) {
        return patientRepository.findByPatientNumberOrName(nameOrNumber).stream()
                .map(p -> (Patient) p)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Patient> findByPatientNumber(String patientNumber) {
        return patientRepository.findByPatientNumber(patientNumber).map(p -> p);
    }

    @Override
    public Patient getById(UUID patientId) {
        return patientRepository.getById(patientId);
    }
}
