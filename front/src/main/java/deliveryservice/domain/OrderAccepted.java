package deliveryservice.domain;

import deliveryservice.domain.*;
import deliveryservice.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private String status;
    private Long customerId;
    private Long orderId;
    private Long foodId;
    private String foodName;
    private String address;
    private Integer qty;
}
