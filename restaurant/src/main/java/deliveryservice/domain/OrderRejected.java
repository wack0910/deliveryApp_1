package deliveryservice.domain;

import deliveryservice.domain.*;
import deliveryservice.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderRejected extends AbstractEvent {

    private Long id;
    private String status;
    private Long orderId;

    public OrderRejected(Restaurant aggregate) {
        super(aggregate);
    }

    public OrderRejected() {
        super();
    }
}
