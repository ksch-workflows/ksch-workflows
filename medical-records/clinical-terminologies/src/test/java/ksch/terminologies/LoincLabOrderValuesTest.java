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

package ksch.terminologies;

import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void should_determine_that_loinc_number_is_available() {
        assertTrue(LoincLabOrderValues.isValid("49054-0"));
    }

    @Test
    public void should_determine_that_loinc_number_is_unavailable() {
        assertFalse(LoincLabOrderValues.isValid("XXXXXXX"));
    }
}
