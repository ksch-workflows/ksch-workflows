package ksch.medicalrecords;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VitalsQueriesImpl implements VitalsQueries {

    private final VitalsRepository vitalsRepository;

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
