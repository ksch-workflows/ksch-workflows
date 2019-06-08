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

package ksch.patientmanagement;

import ksch.patientmanagement.patient.Patient;
import ksch.wicket.FormFieldsPanel;

import static com.google.common.collect.Lists.newArrayList;
import static ksch.wicket.Time.INDIAN_DATE_FORMAT;

public class PatientFormFields extends FormFieldsPanel {

    public PatientFormFields() {
        super("patientFormFields");

        addTextField("inputName");
        addTextField("inputNameFather");
        addTextField("dateOfBirth");
        addTextArea("inputAddress");
        addDropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"));
    }

    public PatientFormFields(Patient patient) {
        super("patientFormFields");

        addTextField("inputName", patient.getName());
        addTextField("inputNameFather", patient.getNameFather());
        addTextField("dateOfBirth", patient.getDateOfBirth().format(INDIAN_DATE_FORMAT));
        addTextArea("inputAddress", patient.getAddress());
        addDropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"), patient.getGender().toString());
    }
}
