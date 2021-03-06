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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ksch.laboratory.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class LabQueriesImpl implements LabQueries {

    private final LabOrderRepository labOrderRepository;

    @Override
    public List<LabOrder> getLabOrders(UUID visitId) {
        return new ArrayList<>(labOrderRepository.findByVisitId(visitId));
    }

    @Override
    public OrderStatus getLabOrderStatus(UUID visitId) {
        var labOrders = labOrderRepository.findByVisitId(visitId);

        if (labOrders.isEmpty()) {
            return NOT_REQUIRED;
        }
        if (labOrders.stream().anyMatch(o -> !o.getStatus().isTerminal())) {
            return PENDING;
        }
        if (labOrders.stream().anyMatch(o -> o.getStatus().equals(LabOrder.Status.DONE))) {
            return DONE;
        }
        return CANCELLED;
    }
}
