package ksch.registration;

import ksch.patientmanagement.patient.Patient;
import ksch.patientmanagement.visit.Visit;
import ksch.patientmanagement.visit.VisitTransactions;
import ksch.patientmanagement.visit.VisitType;
import ksch.testdata.NullVisit;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class MockVisitTransactions {

    @Bean
    public VisitTransactions visitTransactions() {
        return Mockito.spy(new MockVisitTransactionsImpl());
    }

    class MockVisitTransactionsImpl implements VisitTransactions {

        private boolean isActive = false;

        @Override
        public Visit startVisit(Patient patient, VisitType visitType) {
            isActive = true;
            return new NullVisit();
        }

        @Override
        public Visit discharge(Patient patient) {
            isActive = false;
            return new NullVisit();
        }
    }
}
