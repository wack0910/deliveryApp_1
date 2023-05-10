package deliveryservice.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Index_table")
@Data
public class Index {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
