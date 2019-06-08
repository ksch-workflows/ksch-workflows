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

package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;

/**
 * Basis for all services which deal with medical record entries.
 *
 * It is required to make sure that all medical record entries are linked to a {@link Visit}.
 */
interface MedicalRecordTransactions {

    MedicalRecordEntry createMedicalRecordEntry(Visit visit);
}
