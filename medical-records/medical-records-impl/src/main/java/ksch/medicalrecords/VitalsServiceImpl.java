package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static ksch.medicalrecords.VitalsEntity.toVitalsEntity;

@Service
@RequiredArgsConstructor
public class VitalsServiceImpl implements VitalsService {

    private final VitalsRepository vitalsRepository;

    @Override
    public Vitals createMedicalRecordEntry(Visit visit) {
        VitalsEntity vitals = new VitalsEntity(visit);
        return vitalsRepository.save(vitals);
    }

    @Override
    public Vitals save(Vitals vitals) {
        return vitalsRepository.save(toVitalsEntity(vitals));
    }

    @Override
    public Vitals get(UUID vitalsId) {
        return vitalsRepository.findById(vitalsId).orElseThrow(() -> new VitalsNotFoundException(vitalsId));
    }

    class VitalsNotFoundException extends RuntimeException {
        public VitalsNotFoundException(UUID vitalsId) {
            super("Could not find Vitals with ID '" + vitalsId + "' in database.");
        }
    }
}
