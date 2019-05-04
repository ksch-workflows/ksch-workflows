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
