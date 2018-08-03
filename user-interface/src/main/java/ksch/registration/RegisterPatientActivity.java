package ksch.registration;

import ksch.ApplicationFrame;
import lombok.extern.java.Log;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.leanhis.patientmanagement.Gender;
import org.leanhis.patientmanagement.Patient;
import org.leanhis.patientmanagement.PatientService;
import org.wicketstuff.annotation.mount.MountPath;
import util.wicket.FormBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static util.Time.parseDate;

@MountPath("/registration/register-patient")
@AuthorizeInstantiation({"NURSE", "CLERK"})
@Log
public class RegisterPatientActivity extends ApplicationFrame {

    private WebMarkupContainer patientListContainer;

    private WebMarkupContainer noSearchResultsMessageContainer;

    @SpringBean
    private PatientService patientService;

    public RegisterPatientActivity(PageParameters pageParameters) {
        super(pageParameters);

        patientListContainer = new WebMarkupContainer("patientList");
        noSearchResultsMessageContainer = new WebMarkupContainer("noSearchResultsMessage");

        add(patientListContainer);
        add(noSearchResultsMessageContainer);

        patientListContainer.setVisible(false);
        noSearchResultsMessageContainer.setVisible(false);

        add(buildSearchPatientForm());
        add(buildCreatePatientForm());
    }

    private Form buildSearchPatientForm() {
        return new FormBuilder("patientSearchForm")
                .textField("patientSearchTerm")
                .onSubmit(f -> {
                    List<Patient> matchingPatients = patientService.findBy(f.getAndResetValue("patientSearchTerm"));
                    if (matchingPatients.size() > 0) {
                        renderPatientList(matchingPatients);
                    } else {
                        renderNoSearchResultsMessage(true);
                    }
                })
                .build();
    }

    private void renderNoSearchResultsMessage(boolean b) {
        patientListContainer.setVisible(false);
        noSearchResultsMessageContainer.setVisible(b);
    }

    private void renderPatientList(List<Patient> matchingPatients) {
        ListView lv = new ListView<Patient>("patients", matchingPatients) {
            @Override
            protected void populateItem(ListItem<Patient> item) {
                Patient patient = item.getModelObject();

                item.add(new Label("patientNumber", patient.getPatientNumber()));
                item.add(new Label("name", patient.getName()));
                item.add(new Label("gender", patient.getGender()));
                item.add(new Label("age", patientService.getAgeInYears(patient)));

                item.add(new ExternalLink("openPatientDetails", "/registration/edit-patient/" + patient.getId()));
            }
        };

        noSearchResultsMessageContainer.setVisible(false);
        patientListContainer.removeAll(); // required to be able to re-submit search
        patientListContainer.add(lv);
        patientListContainer.setVisible(true);
    }

    private Form buildCreatePatientForm() {
        return new FormBuilder("addPatientForm")
                .textField("inputName")
                .textField("inputNameFather")
                .textField("inputDateOfBirth")
                .textArea("inputAddress")
                .dropDownChoice("inputGender", newArrayList("MALE", "FEMALE", "OTHER"))
                .onSubmit(f -> {
                    Patient patient = Patient.builder()
                            .name(f.getAndResetValue("inputName"))
                            .nameFather(f.getAndResetValue("inputNameFather"))
                            .address(f.getAndResetValue("inputAddress"))
                            .gender(Gender.valueOf(f.getAndResetValue("inputGender").toUpperCase()))
                            .dateOfBirth(parseDate(f.getAndResetValue("inputDateOfBirth")))
                            .build();

                    UUID patientId = patientService.create(patient).getId();

                    throw new RedirectToUrlException("/registration/edit-patient/" + patientId);
                })
                .build();
    }
}
