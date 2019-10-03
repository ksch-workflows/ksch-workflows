package ksch.terminologies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoincLabOrderValuesTest {

    @Test
    public void should_provide_list_of_all_values() {
        assertEquals(1522, LoincLabOrderValues.getAllValues().size());
    }

    @Test
    public void should_provide_access_on_individual_value() {
        LoincLabOrderValue result = LoincLabOrderValues.get("49054-0");
        assertEquals("49054-0", result.getLoincNum());
        assertEquals("25-Hydroxycalciferol [Mass/volume] in Serum or Plasma", result.getLongCommonName());
        assertEquals("Both", result.getOrderObs());
    }
}
