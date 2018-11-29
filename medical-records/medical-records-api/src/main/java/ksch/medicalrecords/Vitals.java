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

    Float getTemperatureInF();

    Integer getPulseInBPM();

    Integer getWeightInKG();
}
