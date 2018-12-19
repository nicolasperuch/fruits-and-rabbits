package dev.peruch.springrabbitmqfruitconsumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableRabbit
@EnableCircuitBreaker
public class SpringRabbitmqFruitConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitmqFruitConsumerApplication.class, args);
	}

}

