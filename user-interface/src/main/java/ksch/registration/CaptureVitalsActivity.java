package ksch.registration;

import ksch.Activity;
import ksch.PatientInfoBar;
import ksch.medicalrecords.Vitals;
import ksch.medicalrecords.VitalsService;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitService;
import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class CaptureVitalsActivity extends Activity {

    @SpringBean
    private VitalsService vitalsService;

    @SpringBean
    private VisitService visitService;

    public CaptureVitalsActivity(UUID vitalsId) {
        Vitals vitals = vitalsService.get(vitalsId);
        Patient patient = visitService.getPatient(vitals.getVisitId());

        add(new PatientInfoBar(patient));
        add(new VitalsForm(vitals));
    }

    @Override
    protected String getActivityTitle() {
        return "Capture vitals";
    }

    @Override
    protected String getPreviousPagePath() {
        return "/registration";
    }

    class VitalsForm extends Form<Void> {

        private final VitalsResource vitalsResource;

        public VitalsForm(Vitals vitals) {
            super("vitalsForm");

            this.vitalsResource = new VitalsResource(vitals);

            setDefaultModel(new CompoundPropertyModel<>(vitalsResource));

            add(new NumberTextField<Integer>("systolicInMmHg"));
            add(new NumberTextField<Integer>("diastolicInMmHg"));
            add(new NumberTextField<Float>("temperatureInF"));
            add(new NumberTextField<Integer>("pulseInBPM"));
            add(new NumberTextField<Integer>("weightInKG"));
        }

        @Override
        protected void onSubmit() {
            vitalsService.save(vitalsResource);
        }
    }

    @Getter
    @Setter
    class VitalsResource implements Vitals, Serializable {

        private final UUID id;

        private final UUID visitId;

        private final LocalDateTime time;

        public VitalsResource(Vitals vitals) {
            this.id = vitals.getId();
            this.visitId = vitals.getVisitId();
            this.time = vitals.getTime();
        }

        private Integer systolicInMmHg;

        private Integer diastolicInMmHg;

        private Float temperatureInF;

        private Integer pulseInBPM;

        private Integer weightInKG;
    }
}
