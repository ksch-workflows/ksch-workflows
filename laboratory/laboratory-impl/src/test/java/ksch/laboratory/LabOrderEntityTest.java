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

import static org.junit.Assert.assertEquals;

public class LabOrderEntityTest {

    @Test
    public void should_build_new_lab_order_entity() {
        LabOrderEntity labOrderEntity = new LabOrderEntity(new LabOrderCode("44907-4"));

        assertEquals(LabOrder.Status.NEW, labOrderEntity.getStatus());
    }
}
