package deliveryservice.domain;

import deliveryservice.DeliveryApplication;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long restaurantId;

    private String address;

    private String status;

    private Long customerId;

    @PostPersist
    public void onPostPersist() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void pick() {
        Picked picked = new Picked(this);
        picked.publishAfterCommit();

        setStatus("Picked");
    }

    public void finish() {
        Finished finished = new Finished(this);
        finished.publishAfterCommit();

        setStatus("Finished");
    }

    public static void updateStatus(CookCompleted cookCompleted) {
        /** Example 1:  new item */
        Delivery delivery = new Delivery();
        delivery.setAddress(cookCompleted.getAddress());
        delivery.setCustomerId(cookCompleted.getCustomerId());
        delivery.setOrderId(cookCompleted.getOrderId());
        delivery.setRestaurantId(cookCompleted.getId());
        delivery.setStatus(cookCompleted.getStatus());
        repository().save(delivery);

        

        /** Example 2:  finding and process
        
        repository().findById(cookCompleted.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

    }
}
