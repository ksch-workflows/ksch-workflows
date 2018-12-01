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
     * @return The heart rate in "beats per minute" (BPM).
     *
     * @see <a href="https://en.wikipedia.org/wiki/Heart_rate">Heart read (wikipedia.org)</a>
     */
    Integer getPulseInBPM();

    /**
     * @return The weight of the patient in kilogram (KG).
     */
    Integer getWeightInKG();
}
