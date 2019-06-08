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

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitType;

import java.time.LocalDateTime;
import java.util.UUID;

public class NullVisit implements Visit {

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getOpdNumber() {
        return null;
    }

    @Override
    public Patient getPatient() {
        return null;
    }

    @Override
    public VisitType getType() {
        return null;
    }

    @Override
    public LocalDateTime getTimeStart() {
        return null;
    }

    @Override
    public LocalDateTime getTimeEnd() {
        return null;
    }
}
