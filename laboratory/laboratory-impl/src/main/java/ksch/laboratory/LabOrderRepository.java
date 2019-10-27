package ksch.laboratory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Component
public interface LabOrderRepository extends CrudRepository<LabOrderEntity, UUID> {

    List<LabOrderEntity> findByVisitId(UUID visitId);
}
