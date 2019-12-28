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

package ksch.registration.dashboard;

import ksch.commons.Time;
import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data transfer object with the responsibility to capture the data for a new patient.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Data_transfer_object">Data transfer object (wikipedia.org)</a>
 */
@Getter
@Setter
class NewPatient implements Patient {
    private final UUID id = null;
    private String name;
    private String nameFather;
    private Gender gender;
    private String dateOfBirth;
    private String address;

    public LocalDate getDateOfBirth() {
        return Time.parseDate(dateOfBirth);
    }

    public String getDateOfBirthAsString() {
        return dateOfBirth;
    }
}
