package dev.peruch.springrabbitmqfruitproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.new-fruit-queue}")
    private String newFruitQueue;
    @Value("${spring.rabbitmq.routing-key-to-new-fruit}")
    private String routingKeyToNewFruitQueue;
    @Value("${spring.rabbitmq.dead-letter}")
    private String deadLetter;
    @Value("${spring.rabbitmq.routing-key-to-dead-letter}")
    private String routingKeyToDeadLetter;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange, true, false);
    }

    @Bean
    public Queue newFruitQueue() {
        return new Queue(newFruitQueue, true, false, false);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(deadLetter, true, false, false);
    }

    @Bean
    public Binding bindindExchangeToNewFruitQueue() {
        return BindingBuilder.bind(newFruitQueue()).to(topicExchange()).with(routingKeyToNewFruitQueue);
    }

    @Bean
    public Binding bindindExchangeToDeadLetter() {
        return BindingBuilder.bind(deadLetterQueue()).to(topicExchange()).with(routingKeyToDeadLetter);
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.declareExchange(topicExchange());
        rabbitAdmin.declareQueue(newFruitQueue());
        rabbitAdmin.declareQueue(deadLetterQueue());
        rabbitAdmin.declareBinding(bindindExchangeToNewFruitQueue());
        rabbitAdmin.declareBinding(bindindExchangeToDeadLetter());
        return rabbitAdmin;
    }
}
