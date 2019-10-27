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

        labCommands.requestExamination(visitId, labOrderCode);

        verify(labOrderRepository).save(labOrderArgumentCaptor.capture());
        LabOrderEntity savedLabOrder = labOrderArgumentCaptor.getValue();
        assertEquals(visitId, savedLabOrder.getVisitId());
        assertEquals(LabOrder.Status.NEW, savedLabOrder.getStatus());
        assertEquals(labOrderCode, savedLabOrder.getLabTest().getRequest());
    }
}
