package org.leanhis.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface EventRepository extends CrudRepository<EventEntity, Integer> {

    @Query("Select e from EventEntity e where pointInTime between :from and :to")
    List<EventEntity> findEvents(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    @Query("Select e from EventEntity e where eventType = :eventType")
    List<EventEntity> findEvents(@Param("eventType") String eventType);
}
