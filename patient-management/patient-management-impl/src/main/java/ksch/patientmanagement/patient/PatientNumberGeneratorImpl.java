package ksch.patientmanagement.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class PatientNumberGeneratorImpl implements PatientNumberGenerator {

    private static final int currentYearWithTwoDigits = Calendar.getInstance().get(Calendar.YEAR) % 100;

    private final PatientNumberIndexRepository patientNumberIndexRepository;

    @Override
    public synchronized String generateOpdNumber() {
        PatientNumberIndex patientNumberIndex = patientNumberIndexRepository.save(new PatientNumberIndex());
        return String.format("%s-%s", currentYearWithTwoDigits, patientNumberIndex);
    }
}
