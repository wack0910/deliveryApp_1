package deliveryservice.domain;

import deliveryservice.domain.*;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "restaurants",
    path = "restaurants"
)
public interface RestaurantRepository
    extends PagingAndSortingRepository<Restaurant, Long> {
        Optional<Restaurant> findByOrderId(Long id);
    }
