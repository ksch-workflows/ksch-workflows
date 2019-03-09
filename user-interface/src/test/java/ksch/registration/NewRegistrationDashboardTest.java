package ksch.registration;

import ksch.WebPageTest;
import org.junit.Before;
import org.junit.Test;

public class NewRegistrationDashboardTest extends WebPageTest {

    @Before
    public void setup() {
        login("user", "pwd");
    }

    @Test
    public void should_render_registration_dashboard() {
        tester.startPage(NewRegistrationDashboardPage.class);
        tester.assertRenderedPage(NewRegistrationDashboardPage.class);
    }

    @Test
    public void should_render_table_with_active_opt_patients() {

    }

    @Test
    public void should_open_patient_details_by_entering_opt_number() {

    }

    @Test
    public void should_open_warning_dialog_if_entered_opt_number_doesnt_exist() {
        
    }

    @Test
    public void should_open_patient_details_by_click_in_opt_patients_table() {

    }

    @Test
    public void should_register_new_patient_via_form_in_dialog() {

    }
}
