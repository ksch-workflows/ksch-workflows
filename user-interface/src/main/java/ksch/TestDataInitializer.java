package ksch;

import ksch.patientmanagement.patient.Gender;
import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.patient.PatientTransactions;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The purpose of this class is to fill the database with some test data after the application startup.
 * In this way manual quality assurance can be simplified.
 *
 * This is only be done if the application was started with the specified Spring profile.
 */
@Component
@Profile("qa")
@Slf4j
@RequiredArgsConstructor
public class TestDataInitializer implements ApplicationRunner {

    private final PatientTransactions patientTransactions;

    private final VisitTransactions visitTransactions;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Initializing test data");

        Patient testPatient = patientTransactions.create(new TestPatient());
        visitTransactions.startVisit(testPatient, VisitType.IPD);
    }
}

class TestPatient implements Patient {

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getPatientNumber() {
        return null;
    }

    @Override
    public String getName() {
        return "Jayadev Mitali";
    }

    @Override
    public String getNameFather() {
        return "Advitiya Sujeet";
    }

    @Override
    public LocalDate getDateOfBirth() {
        return LocalDate.of(2000, 1, 1);
    }

    @Override
    public Gender getGender() {
        return Gender.MALE;
    }

    @Override
    public String getAddress() {
        return "Kirpal Sagar";
    }
}
