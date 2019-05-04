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

import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static ksch.util.CustomAssertions.assertAllPropertiesEqual;

public class PatientEntityTest {

    private Patient patient;

    @Test
    public void should_build_patient_entity() {
        givenPatient();

        PatientEntity e = PatientEntity.toPatientEntity(patient);

        assertAllPropertiesEqual(Patient.class, patient, e);
    }

    private void givenPatient() {
        patient = new Patient() {
            @Override
            public UUID getId() {
                return UUID.fromString("a577731b-7cf2-4cb1-8ceb-13e1bac034fe");
            }

            @Override
            public String getPatientNumber() {
                return "1234-123";
            }

            @Override
            public String getName() {
                return "John C. Doe";
            }

            @Override
            public String getNameFather() {
                return "John's father";
            }

            @Override
            public LocalDate getDateOfBirth() {
                return LocalDate.now();
            }

            @Override
            public Gender getGender() {
                return Gender.OTHER;
            }

            @Override
            public String getAddress() {
                return "North Pole";
            }
        };
    }
}
