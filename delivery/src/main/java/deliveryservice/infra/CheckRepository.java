package deliveryservice.infra;

import deliveryservice.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "checks", path = "checks")
public interface CheckRepository
    extends PagingAndSortingRepository<Check, Long> {
    List<Check> findByOrderId(Long orderId);
}
