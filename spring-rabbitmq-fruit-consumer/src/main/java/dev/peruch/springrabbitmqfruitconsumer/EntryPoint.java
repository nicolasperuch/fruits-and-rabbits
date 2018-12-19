package dev.peruch.springrabbitmqfruitconsumer;

import dev.peruch.springrabbitmqfruitconsumer.client.ExternalClient;
import dev.peruch.springrabbitmqfruitconsumer.dto.FruitDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint {

    @Autowired
    private ExternalClient externalClient;

    @RabbitListener(queues = {"fruit-queue"})
    public void receive(@Payload Message message) {
        //transform message to dto, not now XD
        message.getBody();
        FruitDto fruitDto = new FruitDto("uva", 1);
        externalClient.createFruitIntoExternalClient(fruitDto);
    }
}
