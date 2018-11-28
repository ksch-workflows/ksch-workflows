package ksch.patientmanagement.patient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PatientNumberIndexRepository extends CrudRepository<PatientNumberIndex, Integer> {
}
