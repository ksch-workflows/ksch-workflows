package ksch.patientmanagement.visit;

import ksch.patientmanagement.patient.Patient;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class VisitServiceImpl implements VisitService {

    @Override
    public boolean isActive(Patient patient) {
        // TODO Implement method
        throw new NotImplementedException();
    }

    @Override
    public Visit startVisit(Patient patient, VisitType visitType) {
        // TODO Implement method
        throw new NotImplementedException();
    }

    @Override
    public Visit discharge(Patient patient) {
        // TODO Implement method
        throw new NotImplementedException();
    }
}
