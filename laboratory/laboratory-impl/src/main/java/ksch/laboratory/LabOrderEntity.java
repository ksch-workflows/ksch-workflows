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

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class LabOrderEntity implements LabOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(unique = true)
    private UUID id;

    @Column(nullable = false)
    private UUID visitId;

    @Column(nullable = false)
    private LabTest labTest;

    @Enumerated(EnumType.STRING)
    @Setter
    private Status status;

    public LabOrderEntity(LabOrderCode labOrderCode) {
        labTest = new LabTest(labOrderCode);
        status = Status.NEW;
    }

    @Override
    public LabOrderCode getRequestedTest() {
        return labTest.getRequest();
    }
}
