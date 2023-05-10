package deliveryservice.domain;

import deliveryservice.domain.*;
import deliveryservice.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Finished extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long customerId;
    private String status;
    private Long restaurantId;
    private String address;
}
