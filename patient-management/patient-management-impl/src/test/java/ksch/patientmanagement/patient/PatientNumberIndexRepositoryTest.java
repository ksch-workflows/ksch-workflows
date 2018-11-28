package ksch.patientmanagement.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientNumberIndexRepositoryTest {

    @Autowired
    private PatientNumberIndexRepository repo;

    @Test
    public void should_retrieve_incremented_number_from_database() {
        PatientNumberIndex no1 = repo.save(new PatientNumberIndex());
        PatientNumberIndex no2 = repo.save(new PatientNumberIndex());

        assertEquals("Patient number index doesn't start with expected initial value.",
                1000, no1.getIndex());
        assertEquals("Patient number index didn't get incremented by one for the second value.",
                1001, no2.getIndex());
    }
}
