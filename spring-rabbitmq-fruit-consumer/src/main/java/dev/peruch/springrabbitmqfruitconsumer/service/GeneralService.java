package dev.peruch.springrabbitmqfruitconsumer.service;

import dev.peruch.springrabbitmqfruitconsumer.dto.FruitDto;

public class GeneralService {

    private static final Integer MAX_RETRY_ATTEMPTS = 4;

    public static boolean shouldRetry(FruitDto fruitDto) {
        return fruitDto.getAttempts() < MAX_RETRY_ATTEMPTS;
    }
}
