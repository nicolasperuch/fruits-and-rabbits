package dev.peruch.springrabbitmqfruitconsumer.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dev.peruch.springrabbitmqfruitconsumer.dto.FruitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static dev.peruch.springrabbitmqfruitconsumer.service.GeneralService.shouldRetry;

@Component
public class ExternalClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProducerClient producerClient;

    @HystrixCommand(fallbackMethod = "fallback",
                    ignoreExceptions = ResourceAccessException.class)
    public ResponseEntity createFruitIntoExternalClient(FruitDto fruitDto){
        try {
            return restTemplate.getForEntity("https://googlsdadasdae.com", String.class);
        } catch (Exception e) {
            return producerClient.feedDeadLetter(fruitDto);
        }
    }

    public ResponseEntity fallback(FruitDto fruitDto){
        System.out.println("input: " + fruitDto.toString());
        fruitDto.setAttempts(increaseAttempt(fruitDto));
        return shouldRetry(fruitDto) ?
        producerClient.refeedQueue(fruitDto) :
        producerClient.feedDeadLetter(fruitDto);
    }

    private Integer increaseAttempt(FruitDto fruitDto) {
        return fruitDto.getAttempts() + 1;
    }
}

