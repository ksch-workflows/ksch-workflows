package ksch.medicalrecords;

import ksch.patientmanagement.visit.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ksch.medicalrecords.VitalsEntity.toVitalsEntity;

@Service
@RequiredArgsConstructor
public class VitalsTransactionsImpl implements VitalsTransactions {

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
}
