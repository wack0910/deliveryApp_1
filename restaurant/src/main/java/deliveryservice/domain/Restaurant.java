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
        // OrderRejected orderRejected = new OrderRejected(this);
        // orderRejected.publishAfterCommit();
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
        if(selectCommand.getAccept()){
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();

        setStatus("Accepted");
        }
        else{
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();

        setStatus("Rejected");
        }
    }

    public void start() {
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();

        setStatus("CookStarted");
    }

    public void finish() {
        CookCompleted cookCompleted = new CookCompleted(this);
        cookCompleted.publishAfterCommit();
        
        setStatus("CookFinished");
    }

    public static void updateStatus(Paid paid) {
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        */ 

        /** Example 2:  finding and process*/
        
        repository().findByOrderId(paid.getId()).ifPresent(restaurant->{
            restaurant.setStatus("Paid"); // do something
            repository().save(restaurant);


         });
        

    }

    public static void updateStatus(OrderCancled orderCancled) {
        /** Example 1:  new item 
        Restaurant restaurant = new Restaurant();
        repository().save(restaurant);

        */

        /** Example 2:  finding and process*/
        
        repository().findByOrderId(orderCancled.getId()).ifPresent(restaurant->{
            
            restaurant.setStatus("OrderCancled"); // do something
            repository().save(restaurant);
         });
        

    }

    public static void copyOrder(OrderPlaced orderPlaced) {
        /** Example 1:  new item */
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(orderPlaced.getAddress());
        restaurant.setCustomerId(orderPlaced.getCustomerId());
        restaurant.setFoodId(orderPlaced.getFoodId());
        restaurant.setStatus("NoPaid");
        restaurant.setOrderId(orderPlaced.getId());
        restaurant.setQty(orderPlaced.getQty());
        repository().save(restaurant);

        

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(restaurant->{
            
            restaurant // do something
            repository().save(restaurant);


         });
        */

    }
}
