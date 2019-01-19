package ksch.medicalrecords;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExampleVitals implements Vitals {

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    @Override
    public UUID getVisitId() {
        return UUID.randomUUID();
    }

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    @Override
    public Integer getSystolicInMmHg() {
        return 130;
    }

    @Override
    public Integer getDiastolicInMmHg() {
        return 80;
    }

    @Override
    public Float getTemperatureInF() {
        return 98.4f;
    }

    @Override
    public Integer getPulseInBpm() {
        return 80;
    }

    @Override
    public Integer getWeightInKg() {
        return 73;
    }
}
