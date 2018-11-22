package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.PatientEntity;
import ksch.patientmanagement.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VisitRepositoryTest {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void should_store_visit() {
        PatientEntity patient = createTestPatient("KSA-123", "John Doe");
        VisitEntity visit = VisitEntity.builder()
                .patient(patient)
                .type(VisitType.OPD)
                .timeStart(LocalDateTime.now())
                .build();

        VisitEntity savedVisit = visitRepository.save(visit);

        assertTrue("Could not generate primary key in database", savedVisit.getId() != null);
        assertTrue("Could not find any visit in database.", visitRepository.findAll().iterator().hasNext());
    }

    private PatientEntity createTestPatient(String patientNumber, String name) {
        return patientRepository.save(PatientEntity.builder()
                .dateOfBirth(LocalDate.now())
                .gender(Gender.FEMALE)
                .name(name)
                .patientNumber(patientNumber)
                .build());
    }
}
