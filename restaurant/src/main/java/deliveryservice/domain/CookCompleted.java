package deliveryservice.domain;

import deliveryservice.domain.*;
import deliveryservice.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookCompleted extends AbstractEvent {

    private Long id;
    private Long customerId;
    private Long orderId;
    private Long foodId;
    private String foodName;
    private String status;
    private String address;
    private Integer qty;

    public CookCompleted(Restaurant aggregate) {
        super(aggregate);
    }

    public CookCompleted() {
        super();
    }
}
