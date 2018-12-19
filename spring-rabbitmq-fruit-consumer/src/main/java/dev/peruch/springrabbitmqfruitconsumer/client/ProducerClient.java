package dev.peruch.springrabbitmqfruitconsumer.client;

import dev.peruch.springrabbitmqfruitconsumer.dto.FruitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProducerClient {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity refeedQueue(FruitDto fruitDto) {
        return restTemplate.postForObject("http://localhost:8080/fruit", fruitDto, ResponseEntity.class);
    }

    public ResponseEntity feedDeadLetter(FruitDto fruitDto) {
        return restTemplate.postForObject("http://localhost:8080/spoiled-fruit", fruitDto, ResponseEntity.class);
    }
}