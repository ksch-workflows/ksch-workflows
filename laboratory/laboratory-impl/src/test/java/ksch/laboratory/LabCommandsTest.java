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

package ksch.laboratory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LabCommandsTest {

    @InjectMocks
    private LabCommandsImpl labCommands;

    @Mock
    private LabOrderRepository labOrderRepository;

    @Captor
    private ArgumentCaptor<LabOrderEntity> labOrderArgumentCaptor;

    @Test
    public void should_request_blood_examination() {
        UUID visitId = UUID.randomUUID();
        LabOrderCode labOrderCode = new LabOrderCode("44907-4");

        labCommands.requestLaboratoryTest(visitId, labOrderCode);

        verify(labOrderRepository).save(labOrderArgumentCaptor.capture());
        LabOrderEntity savedLabOrder = labOrderArgumentCaptor.getValue();
        assertEquals(visitId, savedLabOrder.getVisitId());
        assertEquals(LabOrder.Status.NEW, savedLabOrder.getStatus());
        assertEquals(labOrderCode, savedLabOrder.getLabTest().getRequest());
    }
}
