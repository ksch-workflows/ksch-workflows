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

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LabCommandsImpl implements LabCommands {

    private final LabOrderRepository labOrderRepository;

    @Override
    public LabOrder requestLaboratoryTest(UUID visitId, LabOrderCode labOrderCode) {
        var labOrder = LabOrderEntity.builder()
                .visitId(visitId)
                .labTest(new LabTest(labOrderCode))
                .status(LabOrder.Status.NEW)
                .build();
        return labOrderRepository.save(labOrder);
    }

    @Override
    public LabOrder cancel(UUID labOrderId) {
        var labOrderEntity = labOrderRepository.findById(labOrderId).orElseThrow();
        labOrderEntity.setStatus(LabOrder.Status.ABORTED);
        return labOrderRepository.save(labOrderEntity);
    }
}
