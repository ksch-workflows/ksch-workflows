package ksch.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void should_save_event() {
        EventEntity event = EventEntity.builder()
                .pointInTime(now())
                .eventType("test")
                .build();

        EventEntity savedEvent = eventRepository.save(event);

        assertTrue("Save event with a generated serial number.", savedEvent.getId() > 0);
    }
}
