package deliveryservice.domain;

import deliveryservice.FrontApplication;
import deliveryservice.domain.Paid;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String status;

    private Integer totalPrice;

    @PostPersist
    public void onPostPersist() {
        Paid paid = new Paid(this);
        paid.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = FrontApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void pay(OrderPlaced orderPlaced) {
        /** Example 1:  new item */
        Payment payment = new Payment();
        payment.setOrderId(orderPlaced.getId());
        payment.setTotalPrice(orderPlaced.getTotalPrice());
        payment.setStatus("Ordered");
        repository().save(payment);

        Paid paid = new Paid(payment);
        paid.publishAfterCommit();
        

        /** Example 2:  finding and process 
        
        repository().findByOrderId(orderPlaced.getId()).ifPresent(payment->{
            
            payment.setStatus("Ordered"); // do something
            repository().save(payment);

            Paid paid = new Paid(payment);
            paid.publishAfterCommit();

         });
        
*/
    }
}
