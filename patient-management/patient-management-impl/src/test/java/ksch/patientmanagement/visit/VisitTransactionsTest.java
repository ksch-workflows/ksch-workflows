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
import java.util.UUID;

import static ksch.patientmanagement.patient.PatientEntity.toPatientEntity;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitTransactionsTest {

    @Autowired
    private VisitTransactions visitTransactions;

    @Autowired
    private VisitQueries visitQueries;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void should_start_visit() {
        Patient patient = createTestPatient();

        Visit visit = visitTransactions.startVisit(patient, VisitType.IPD);
        boolean hasActiveVisit = visitQueries.isActive(patient);

        assertTrue("Patient has no active visit even though it has just been started", hasActiveVisit);
        assertNotNull("Database did not generate a primariy key for the database record for the visit", visit.getId());
    }

    @Test
    public void should_discharge_patient() {
        Patient patient = createTestPatient();
        visitTransactions.startVisit(patient, VisitType.IPD);

        Visit visit = visitTransactions.discharge(patient);
        boolean hasActiveVisit = visitQueries.isActive(patient);

        assertFalse("Patient visit is still active even though he has just been discharged",
                hasActiveVisit);
        assertNotNull("Time of discharge not set",
                visit.getTimeEnd());
        assertTrue("End time of the visit is not after the start time of the visit",
                visit.getTimeEnd().isAfter(visit.getTimeStart()));
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
