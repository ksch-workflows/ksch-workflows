package ksch.patientmanagement;


import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientQueries;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.VisitQueries;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import lombok.Getter;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralPatientInformation extends Panel {

    private final Patient patient;

    private final Button startVisitButton;

    private final AjaxLink<Void> dischargeButton;

    @SpringBean
    private PatientTransactions patientTransactions;

    @SpringBean
    private PatientQueries patientQueries;

    @SpringBean
    private VisitTransactions visitTransactions;

    @SpringBean
    private VisitQueries visitQueries;


    public GeneralPatientInformation(Patient patient) {
        super("generalPatientInformation");

        this.patient = patient;
        this.startVisitButton = createStartVisitButton();
        this.dischargeButton = createDischargeButton();

        add(startVisitButton);
        add(dischargeButton);
        add(new StartVisitForm());
    }

    private Button createStartVisitButton() {
        Button btn = new Button("startVisitButton");
        btn.setOutputMarkupId(true);
        btn.setOutputMarkupPlaceholderTag(true);

        if (visitQueries.isActive(patient)) {
            btn.setVisible(false);
        }

        return btn;
    }

    private AjaxLink<Void> createDischargeButton() {
        AjaxLink<Void> btn = new AjaxLink<>("dischargeButton") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                visitTransactions.discharge(patient);

                startVisitButton.setVisible(true);
                dischargeButton.setVisible(false);

                target.add(startVisitButton);
                target.add(dischargeButton);
            }
        };
        btn.setOutputMarkupId(true);
        btn.setOutputMarkupPlaceholderTag(true);

        if (!visitQueries.isActive(patient)) {
            btn.setVisible(false);
        }

        return btn;
    }

    @Getter
    class StartVisitForm extends Form<Void> {

        private final List<String> visitTypes = Arrays.stream(VisitType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());

        private String visitType;

        public StartVisitForm() {
            super("startVisitForm");

            PropertyModel<String> visitType = new PropertyModel<>(this, "visitType");
            RadioChoice<String> visitTypeSelection = new RadioChoice<>("visitTypeSelection", visitType, visitTypes);
            add(visitTypeSelection);
        }

        @Override
        protected void onSubmit() {
            visitTransactions.startVisit(patient, VisitType.valueOf(visitType));

            startVisitButton.setVisible(false);
            dischargeButton.setVisible(true);
        }
    }
}
