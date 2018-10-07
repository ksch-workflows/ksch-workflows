package ksch.administration;

import ksch.Activity;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.leanhis.PatientReport;

import static java.time.LocalDateTime.now;

public class PatientReportActivity extends Activity {

    @SpringBean
    private PatientReport patientReport;

    public PatientReportActivity() {
        add(new Label("numberOfNewPatient", patientReport.getNumberOfNewPatients(now().minusDays(7), now())));
    }

    @Override
    public String getActivityTitle() {
        return "Patient report";
    }

    @Override
    public String getPreviousPagePath() {
        return "/administration";
    }
}
