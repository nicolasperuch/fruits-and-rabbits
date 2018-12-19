package dev.peruch.springrabbitmqfruitproducer.service;

public abstract class FruitProperties {
    protected final String FRUIT_EXCHANGE = "fruit-exchange";
    protected final String NEW_FRUIT_ROUTING_KEY = "new-fruit";
    protected final String DEAD_LETTER = "fruit-dead-letter";
}
