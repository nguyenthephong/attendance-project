package edu.miu.cs.cs544.receiver;

import edu.miu.cs.cs544.service.contract.EmailPayload;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @KafkaListener(topics = {"email"})
    public void createAccount(@Payload EmailPayload message){
        System.out.println("message: "+message);
    }

}
