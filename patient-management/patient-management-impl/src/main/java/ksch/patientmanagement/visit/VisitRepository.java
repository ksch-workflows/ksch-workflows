package ksch.patientmanagement.visit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Component
@Transactional
public interface VisitRepository extends CrudRepository<VisitEntity, UUID> {

    List<VisitEntity> findAllByPatientId(UUID id);
}
