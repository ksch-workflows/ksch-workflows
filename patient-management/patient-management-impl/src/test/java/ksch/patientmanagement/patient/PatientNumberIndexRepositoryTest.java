/*
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
