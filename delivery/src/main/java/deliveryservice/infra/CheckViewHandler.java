package deliveryservice.infra;

import deliveryservice.config.kafka.KafkaProcessor;
import deliveryservice.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CheckViewHandler {

    @Autowired
    private CheckRepository checkRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookCompleted_then_CREATE_1(
        @Payload CookCompleted cookCompleted
    ) {
        try {
            if (!cookCompleted.validate()) return;

            // view 객체 생성
            Check check = new Check();
            // view 객체에 이벤트의 Value 를 set 함
            // view 레파지 토리에 save
            checkRepository.save(check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookCompleted_then_UPDATE_1(
        @Payload CookCompleted cookCompleted
    ) {
        try {
            if (!cookCompleted.validate()) return;
            // view 객체 조회

            List<Check> checkList = checkRepository.findByOrderId(
                cookCompleted.getOrderId()
            );
            for (Check check : checkList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                check.setStatus(cookCompleted.getStatus());
                // view 레파지 토리에 save
                checkRepository.save(check);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
