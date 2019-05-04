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

/**
 * Basic vital signs for the bodily condition of the patient.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Vital_signs">Vital signs (wikipedia.org)</a>
 */
public interface Vitals extends MedicalRecordEntry {

    /**
     * @return The pressure in blood vessels during heart beats in "millimeters of mercury" (mmHg).
     *
     * @see <a href="https://www.cdc.gov/bloodpressure/measure.htm">Measuring Blood Pressure (cdc.gov)</a>
     * @see <a href="http://www.bloodpressureuk.org/BloodPressureandyou/Thebasics/Bloodpressurechart">
     *     Blood pressure chart (bloodpressureuk.org)</a>
     */
    Integer getSystolicInMmHg();

    /**
     * @return The pressure in blood vessels between heart beats in "millimeters of mercury" (mmHg).
     *
     * @see <a href="https://www.cdc.gov/bloodpressure/measure.htm">Measuring Blood Pressure (cdc.gov)</a>
     * @see <a href="http://www.bloodpressureuk.org/BloodPressureandyou/Thebasics/Bloodpressurechart">
     *     Blood pressure chart (bloodpressureuk.org)</a>
     */
    Integer getDiastolicInMmHg();

    /**
     * @return The body temperature in degrees Fahrenheit (°F).
     *
     * @see <a href="https://en.wikipedia.org/wiki/Human_body_temperature">Human body temperature (wikipedia.org)</a> ""
     */
    Float getTemperatureInF();

    /**
     * @return The heart rate in "beats per minute" (bpm).
     *
     * @see <a href="https://en.wikipedia.org/wiki/Heart_rate">Heart rate (wikipedia.org)</a>
     */
    Integer getPulseInBpm();

    /**
     * @return The weight of the patient in kilogram (kg).
     */
    Integer getWeightInKg();
}
