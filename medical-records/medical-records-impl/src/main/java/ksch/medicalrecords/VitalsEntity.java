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

package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VitalsEntity implements Vitals {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(unique = true, nullable = false)
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private UUID visitId;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime time;

    @Column
    private Integer systolicInMmHg;

    @Column
    private Integer diastolicInMmHg;

    @Column
    private Float temperatureInF;

    @Column
    private Integer pulseInBpm;

    @Column
    private Integer weightInKg;

    public VitalsEntity(Visit visit) {
        this.visitId = visit.getId();
        this.setTime(now());
    }

    public static VitalsEntity toVitalsEntity(Vitals vitals) {
        return VitalsEntity.builder()
                .id(vitals.getId())
                .visitId(vitals.getVisitId())
                .time(vitals.getTime())
                .systolicInMmHg(vitals.getSystolicInMmHg())
                .diastolicInMmHg(vitals.getDiastolicInMmHg())
                .temperatureInF(vitals.getTemperatureInF())
                .pulseInBpm(vitals.getPulseInBpm())
                .weightInKg(vitals.getWeightInKg())
                .build();
    }
}
