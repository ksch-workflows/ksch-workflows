package ksch.terminologies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoincLabOrderValuesTest {

    @Test
    public void should_provide_list_of_all_values() {
        assertEquals(1234, LoincLabOrderValues.getAllValues().size());
    }
}
