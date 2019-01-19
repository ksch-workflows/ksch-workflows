package ksch.patientmanagement.patient;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Component
public interface PatientRepository extends CrudRepository<PatientEntity, UUID> {

    @Query("Select p from PatientEntity p where " +
            "lower(p.name) like lower(concat('%',:patientNumberOrName,'%')) or " +
            "lower(p.patientNumber) like lower(concat('%',:patientNumberOrName,'%'))" )
    List<PatientEntity> findByPatientNumberOrName(@Param("patientNumberOrName") String patientNumberOrName);

    PatientEntity getById(UUID patientId);

    Optional<PatientEntity> findByPatientNumber(String patientNumber);
}
