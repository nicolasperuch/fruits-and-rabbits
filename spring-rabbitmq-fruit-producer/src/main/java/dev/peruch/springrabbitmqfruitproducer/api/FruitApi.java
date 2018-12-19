package dev.peruch.springrabbitmqfruitproducer.api;

import dev.peruch.springrabbitmqfruitproducer.api.dto.FruitDto;
import dev.peruch.springrabbitmqfruitproducer.model.FruitModel;
import dev.peruch.springrabbitmqfruitproducer.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class FruitApi {

    @Autowired
    private FruitService fruitService;

    @PostMapping("/fruit")
    public ResponseEntity addFruit(@RequestBody FruitDto fruitDto){
        FruitModel fruitModel = new FruitModel(fruitDto);
        fruitService.sendFruitToExchange(fruitModel);
        return ok("fruit sent to queue :)");
    }

    @PostMapping("/spoiled-fruit")
    public ResponseEntity discartFruit(@RequestBody FruitDto fruitDto){
        FruitModel fruitModel = new FruitModel(fruitDto);
        fruitService.sendFruitToDeadLetter(fruitModel);
        return ok().build();
    }
}
