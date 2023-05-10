package deliveryservice.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class SelectCommand {

    private Boolean accept;
}
