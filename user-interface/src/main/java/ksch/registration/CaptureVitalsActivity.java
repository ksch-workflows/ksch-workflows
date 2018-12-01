package ksch.registration;

import ksch.Activity;
import ksch.PatientInfoBar;
import ksch.medicalrecords.Vitals;
import ksch.medicalrecords.VitalsService;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class CaptureVitalsActivity extends Activity {

    @SpringBean
    private VitalsService vitalsService;

    @SpringBean
    private VisitService visitService;

    private final UUID visitId;

    public CaptureVitalsActivity(UUID visitId) {
        this.visitId = visitId;

        Visit visit = visitService.get(visitId);
        Patient patient = visit.getPatient();

        add(new PatientInfoBar(patient));
        add(new VitalsForm());
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

        public VitalsForm() {
            super("vitalsForm");

            this.vitalsResource = new VitalsResource();

            setDefaultModel(new CompoundPropertyModel<>(vitalsResource));

            add(new NumberTextField<Integer>("systolicInMmHg"));
            add(new NumberTextField<Integer>("diastolicInMmHg"));
            add(new NumberTextField<Float>("temperatureInF")); // FIXME Float value is not handled properly in UI
            add(new NumberTextField<Integer>("pulseInBPM"));
            add(new NumberTextField<Integer>("weightInKG"));
        }

        @Override
        protected void onSubmit() {
            if (isFirstFormSubmission()) {
                Vitals medicalRecordEntry = vitalsService.createMedicalRecordEntry(visitService.get(visitId));

                vitalsResource.setId(medicalRecordEntry.getId());
                vitalsResource.setVisitId(medicalRecordEntry.getVisitId());
                vitalsResource.setTime(medicalRecordEntry.getTime());
            }

            vitalsService.save(vitalsResource);
            log.debug("Saved Vitals: " + vitalsResource);
        }

        private boolean isFirstFormSubmission() {
            return vitalsResource.getId() == null;
        }
    }

    @Getter
    @Setter
    @ToString
    class VitalsResource implements Vitals, Serializable {

        private UUID id;

        private UUID visitId;

        private LocalDateTime time;

        private Integer systolicInMmHg;

        private Integer diastolicInMmHg;

        private Float temperatureInF;

        private Integer pulseInBPM;

        private Integer weightInKG;
    }
}
