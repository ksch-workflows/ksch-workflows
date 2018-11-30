package ksch.medicalrecords;

// TODO Add comments for return values
public interface Vitals extends MedicalRecordEntry {

    /**
     * @see "http://www.bloodpressureuk.org/BloodPressureandyou/Thebasics/Bloodpressurechart"
     */
    Integer getSystolicInMmHg();

    /**
     * @see "http://www.bloodpressureuk.org/BloodPressureandyou/Thebasics/Bloodpressurechart"
     */
    Integer getDiastolicInMmHg();

    /**
     * @see "https://en.wikipedia.org/wiki/Human_body_temperature"
     */
    Float getTemperatureInF();

    /**
     * @see "https://en.wikipedia.org/wiki/Heart_rate"
     */
    Integer getPulseInBPM();

    Integer getWeightInKG();
}
