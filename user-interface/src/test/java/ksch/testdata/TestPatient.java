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

package ksch.testdata;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;

import java.time.LocalDate;
import java.util.UUID;

public class TestPatient implements Patient {

    private final UUID id;

    public TestPatient() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return "John Doe";
    }

    @Override
    public String getNameFather() {
        return null;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return LocalDate.now();
    }

    @Override
    public Gender getGender() {
        return Gender.MALE;
    }

    @Override
    public String getAddress() {
        return "Kirpal Sagar";
    }
}
