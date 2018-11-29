package ksch.medicalrecords;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VitalsRepository extends CrudRepository<VitalsEntity, UUID> {
}
