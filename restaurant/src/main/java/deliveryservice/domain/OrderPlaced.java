package deliveryservice.domain;

import deliveryservice.domain.*;
import deliveryservice.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private Long customerId;
    private Integer totalPrice;
    private String status;
    private String address;
    private Long foodId;
    private String foodName;
    private Integer qty;
}
