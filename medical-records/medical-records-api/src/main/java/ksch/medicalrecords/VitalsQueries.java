package ksch.medicalrecords;

import java.util.UUID;

public interface VitalsQueries {

    Vitals get(UUID vitalsId);
}
