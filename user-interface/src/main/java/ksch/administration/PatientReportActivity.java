package ksch.administration;

import ksch.Activity;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.leanhis.PatientReport;
import org.wicketstuff.annotation.mount.MountPath;

import static java.time.LocalDateTime.now;

@MountPath("/administration/patient-report")
@AuthorizeInstantiation({"ADMINISTRATOR"})
public class PatientReportActivity extends Activity {

    @SpringBean
    private PatientReport patientReport;

    public PatientReportActivity(PageParameters pageParameters) {
        super(pageParameters);

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
