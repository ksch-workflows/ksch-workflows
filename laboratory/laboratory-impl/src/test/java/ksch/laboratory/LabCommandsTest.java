package ksch.laboratory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class LabCommandsTest {

    @InjectMocks
    private LabCommandsImpl labCommands;

    @Test
    public void should_request_blood_examination() {
        UUID opdNumber = UUID.randomUUID();
        List<LabOrderCode> tests = LabOrderCode.labOrderCodes("44907-4", "3299-5");

        LabOrder labOrder = labCommands.requestExamination(opdNumber, tests);

        assertNotNull(labOrder);
    }

}
