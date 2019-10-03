package ksch.laboratory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class LabCommandsTest {

    @InjectMocks
    private LabCommandsImpl labCommands;

    @Test
    public void should_request_blood_examination() {
        UUID opdNumber = UUID.randomUUID();

        List<LabOrderCode> tests = Collections.emptyList();
        tests.add(new LabOrderCode("44907-4"));
        tests.add(new LabOrderCode("3299-5"));

        //UUID labExaminationId = labCommands.requestExamination(opdNumber, tests);
    }

    @Test
    public void should_request_urin_examination() {

    }

    @Test
    public void should_request_stool_examination() {

    }

}
