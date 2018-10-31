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
