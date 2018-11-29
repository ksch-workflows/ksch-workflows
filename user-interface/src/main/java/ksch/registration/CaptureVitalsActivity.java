package ksch.registration;

import ksch.Activity;
import ksch.medicalrecords.Vitals;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.PackageResourceReference;

public class CaptureVitalsActivity extends Activity {

    private final Vitals vitals;

    public CaptureVitalsActivity(Vitals vitals) {
        this.vitals = vitals;
    }

    @Override
    protected String getActivityTitle() {
        return "Capture vitals";
    }

    @Override
    protected String getPreviousPagePath() {
        return "/registration";
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        PackageResourceReference cssFile = new PackageResourceReference(this.getClass(), "CaptureVitalsActivity.css");
        CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);

        response.render(cssItem);
    }
}
