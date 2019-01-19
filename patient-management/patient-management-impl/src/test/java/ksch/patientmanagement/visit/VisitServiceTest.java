package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void should_retrieve_visit_entity_by_id() {
        Patient patient = createTestPatient();
        Visit visit = visitService.startVisit(patient, VisitType.IPD);

        Visit retrievedVisit = visitService.get(visit.getId());

        assertNotNull("Could not retrieve existing Visit entity by its technical identifier.", retrievedVisit);
    }

    @Test
    public void should_not_be_active_for_new_patient() {
        Patient patient = createTestPatient();

        boolean hasActiveVisit = visitService.isActive(patient);

        assertFalse("Patient has an active visit while it was actually not yet started", hasActiveVisit);
    }

    @Test
    public void should_retrieve_active_visit_entity() {
        Patient patient = createTestPatient();
        visitService.startVisit(patient, VisitType.IPD);

        Optional<Visit> activeVisit = visitService.getActiveVisit(patient);

        assertTrue("Could not find an active visit for the test patient.", activeVisit.isPresent());
    }

    @Test
    public void should_retrieve_emtpy_visit_entity_if_no_active_visit() {
        Patient patient = createTestPatient();

        Optional<Visit> activeVisit = visitService.getActiveVisit(patient);

        assertFalse("There should not be an active visit for the test patient.", activeVisit.isPresent());
    }

    @Test
    public void should_start_visit() {
        Patient patient = createTestPatient();

        Visit visit = visitService.startVisit(patient, VisitType.IPD);
        boolean hasActiveVisit = visitService.isActive(patient);

        assertTrue("Patient has no active visit even though it has just been started", hasActiveVisit);
        assertNotNull("Database did not generate a primariy key for the database record for the visit", visit.getId());
    }

    @Test
    public void should_discharge_patient() {
        Patient patient = createTestPatient();
        visitService.startVisit(patient, VisitType.IPD);

        Visit visit = visitService.discharge(patient);
        boolean hasActiveVisit = visitService.isActive(patient);

        assertFalse("Patient visit is still active even though he has just been discharged",
                hasActiveVisit);
        assertNotNull("Time of discharge not set",
                visit.getTimeEnd());
        assertTrue("End time of the visit is not after the start time of the visit",
                visit.getTimeEnd().isAfter(visit.getTimeStart()));
    }

    @Test
    public void test_access_on_patient() {
        Patient patient = createTestPatient();
        Visit visit = visitService.startVisit(patient, VisitType.IPD);

        Patient retrievedPatient = visitService.getPatient(visit.getId());

        assertEquals(patient.getId(), retrievedPatient.getId());
    }

    private Patient createTestPatient() {
        return patientRepository.save(toPatientEntity(buildTestPatient()));
    }

    private Patient buildTestPatient() {
        return new Patient() {
            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }

            @Override
            public String getPatientNumber() {
                return "0815-" + UUID.randomUUID();
            }

            @Override
            public String getName() {
                return "John";
            }

            @Override
            public String getNameFather() {
                return null;
            }

            @Override
            public LocalDate getDateOfBirth() {
                return LocalDate.now();
            }

            @Override
            public Gender getGender() {
                return Gender.MALE;
            }

            @Override
            public String getAddress() {
                return "Jena, Germany";
            }
        };
    }
}
