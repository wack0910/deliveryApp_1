package deliveryservice.domain;

import deliveryservice.FrontApplication;
import deliveryservice.domain.OrderCancled;
import deliveryservice.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    private Integer totalPrice;

    private String status;

    private String address;

    private Long foodId;

    private String foodName;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        OrderCancled orderCancled = new OrderCancled(this);
        orderCancled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = FrontApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public static void notify(CookCompleted cookCompleted) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(cookCompleted.getOrderId()).ifPresent(order->{
            
            order.setStatus(cookCompleted.getStatus()); // do something
            repository().save(order);


         });
        

    }

    public static void notify(CookStarted cookStarted) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process*/
        
        repository().findById(cookStarted.getOrderId()).ifPresent(order->{
            
            order.setStatus(cookStarted.getStatus()); // do something
            repository().save(order);


         });
        

    }

    public static void notify(OrderRejected orderRejected) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process*/
        
        repository().findById(orderRejected.getOrderId()).ifPresent(order->{
            
            order.setStatus(orderRejected.getStatus()); // do something
            repository().save(order);


         });
        

    }

    public static void notify(OrderAccepted orderAccepted) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process*/
        
        repository().findById(orderAccepted.getOrderId()).ifPresent(order->{
            
            order.setStatus(orderAccepted.getStatus()); // do something
            repository().save(order);


         });
        

    }

    public static void notify(Picked picked) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process*/
        
        repository().findById(picked.getOrderId()).ifPresent(order->{
            
            order.setStatus(picked.getStatus()); // do something
            repository().save(order);


         });
        

    }

    public static void notify(Finished finished) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process*/
        
        repository().findById(finished.getOrderId()).ifPresent(order->{
            
            order.setStatus(finished.getStatus()); // do something
            repository().save(order);


         });
        

    }
}
