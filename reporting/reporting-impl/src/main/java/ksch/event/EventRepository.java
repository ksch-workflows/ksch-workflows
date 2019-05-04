/**
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
package ksch.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface EventRepository extends CrudRepository<EventEntity, Integer> {

    default List<EventEntity> findEvents(Class eventType, LocalDateTime from, LocalDateTime to) {
        return findEvents(eventType.getName(), from, to);
    }

    @Query("Select e from EventEntity e where pointInTime between :from and :to and eventType = :eventType")
    List<EventEntity> findEvents(
            @Param("eventType") String eventType,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}
