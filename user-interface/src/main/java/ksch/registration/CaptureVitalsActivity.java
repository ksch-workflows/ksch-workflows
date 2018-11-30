package ksch.registration;

import ksch.Activity;
import ksch.medicalrecords.Vitals;
import ksch.medicalrecords.VitalsService;
import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.Serializable;
import java.util.UUID;

public class CaptureVitalsActivity extends Activity {

    @SpringBean
    private VitalsService vitalsService;

    public CaptureVitalsActivity(UUID vitalsId) {
        add(new VitalsForm(vitalsService.get(vitalsId)));
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

        public VitalsResource(Vitals vitals) {
            this.id = vitals.getId();
            this.visitId = vitals.getVisitId();
        }

        private Integer systolicInMmHg;

        private Integer diastolicInMmHg;

        private Float temperatureInF;

        private Integer pulseInBPM;

        private Integer weightInKG;
    }
}
