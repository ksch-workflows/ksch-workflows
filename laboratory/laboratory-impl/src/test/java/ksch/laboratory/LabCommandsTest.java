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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LabCommandsTest {

    @InjectMocks
    private LabCommandsImpl labCommands;

    @Mock
    private LabOrderRepository labOrderRepository;

    @Captor
    private ArgumentCaptor<LabOrderEntity> labOrderArgumentCaptor;

    @Before
    public void setup() {
        when(labOrderRepository.save(any(LabOrderEntity.class))).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void should_request_blood_examination() {
        var visitId = randomUUID();
        var labOrderCode = new LabOrderCode("44907-4");

        labCommands.requestLaboratoryTest(visitId, labOrderCode);

        verify(labOrderRepository).save(labOrderArgumentCaptor.capture());
        LabOrderEntity savedLabOrder = labOrderArgumentCaptor.getValue();
        assertEquals(visitId, savedLabOrder.getVisitId());
        assertEquals(LabOrder.Status.NEW, savedLabOrder.getStatus());
        assertEquals(labOrderCode, savedLabOrder.getLabTest().getRequest());
    }

    @Test
    public void should_cancel_lab_order() {
        var labOrder = createLabOrder();

        labCommands.cancel(labOrder.getId());

        verify(labOrderRepository, times(2)).save(labOrderArgumentCaptor.capture());
        assertEquals(LabOrder.Status.CANCELED, labOrderArgumentCaptor.getValue().getStatus());
    }

    private LabOrder createLabOrder() {
        var labOrderCode = new LabOrderCode("44907-4");
        var labOrder = (LabOrderEntity) labCommands.requestLaboratoryTest(randomUUID(), labOrderCode);
        labOrder.setId(randomUUID());
        when(labOrderRepository.findById(labOrder.getId())).thenReturn(Optional.of(labOrder));
        return labOrder;
    }
}
