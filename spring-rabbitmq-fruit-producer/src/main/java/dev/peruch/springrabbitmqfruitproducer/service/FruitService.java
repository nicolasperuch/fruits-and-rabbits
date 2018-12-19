package dev.peruch.springrabbitmqfruitproducer.service;

import dev.peruch.springrabbitmqfruitproducer.model.FruitModel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitService extends FruitProperties{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendFruitToExchange(FruitModel fruitModel){
        rabbitTemplate.send(FRUIT_EXCHANGE, NEW_FRUIT_ROUTING_KEY, buildMessage(fruitModel));
    }

    public void sendFruitToDeadLetter(FruitModel fruitModel){
        rabbitTemplate.send(FRUIT_EXCHANGE, DEAD_LETTER, buildMessage(fruitModel));
    }

    public Message buildMessage(FruitModel fruitModel){
        String bodyMessage = fruitModel.getFruitName();
        return new Message(bodyMessage.getBytes(), new MessageProperties());
    }
}
