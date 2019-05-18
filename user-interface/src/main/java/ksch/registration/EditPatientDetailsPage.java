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

package ksch.registration;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.UUID;

@MountPath("/registration/edit-patient/${id}")
@AuthorizeInstantiation({"NURSE", "CLERK"})
public class EditPatientDetailsPage extends RegistrationPage {

    private final UUID patientId;

    public EditPatientDetailsPage(PageParameters pageParameters) {
        super(pageParameters);

        patientId = UUID.fromString(pageParameters.get("id").toString());
    }

    @Override
    protected Panel getContent() {
        return new EditPatientDetailsActivity(patientId);
    }
}
