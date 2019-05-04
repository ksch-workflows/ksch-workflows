/**
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
