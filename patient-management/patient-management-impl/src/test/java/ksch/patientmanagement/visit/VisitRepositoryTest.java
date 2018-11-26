package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientEntity;
import ksch.patientmanagement.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

        VisitEntity visit = createNewVisit(patient);

        assertTrue("Could not generate primary key in database", visit.getId() != null);
        assertTrue("Could not find any visit in database.", visitRepository.findAll().iterator().hasNext());
    }

    @Test
    public void should_find_visits_by_patient_id() {
        PatientEntity patient = createTestPatient("KSA-124", "John Doe");
        VisitEntity visit = createNewVisit(patient);

        List<VisitEntity> retrievedVisits = visitRepository.findAllByPatientId(patient.getId());

        assertEquals(1, retrievedVisits.size());
    }

    private VisitEntity createNewVisit(PatientEntity patient) {
        VisitEntity visit = VisitEntity.builder()
                .patient(patient)
                .type(VisitType.OPD)
                .timeStart(LocalDateTime.now())
                .build();

        return visitRepository.save(visit);
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
