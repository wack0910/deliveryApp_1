package deliveryservice.domain;

import deliveryservice.RestaurantApplication;
import deliveryservice.domain.OrderRejected;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Restaurant_table")
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    private Long orderId;

    private Long foodId;

    private String foodName;

    private String status;

    private String address;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    @PreUpdate
    public void onPreUpdate() {}

    public static RestaurantRepository repository() {
        RestaurantRepository restaurantRepository = RestaurantApplication.applicationContext.getBean(
            RestaurantRepository.class
        );
        return restaurantRepository;
    }

    public void select(SelectCommand selectCommand) {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    public void start() {
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    public void finish() {
        CookCompleted cookCompleted = new CookCompleted(this);
        cookCompleted.publishAfterCommit();
    }

    public static void updateStatus(Paid paid) {
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(restaurant->{
            
            restaurant // do something
            repository().save(restaurant);


         });
        */

    }

    public static void updateStatus(OrderCancled orderCancled) {
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancled.get???()).ifPresent(restaurant->{
            
            restaurant // do something
            repository().save(restaurant);


         });
        */

    }

    public static void copyOrder(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(restaurant->{
            
            restaurant // do something
            repository().save(restaurant);


         });
        */

    }
}
