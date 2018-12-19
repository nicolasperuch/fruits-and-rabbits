package dev.peruch.springrabbitmqfruitproducer.model;

import dev.peruch.springrabbitmqfruitproducer.api.dto.FruitDto;

import static java.util.Objects.isNull;

public class FruitModel {

    private String fruitName;
    private Integer attempts;

    public FruitModel(FruitDto fruitDto) {
        this.fruitName = fruitDto.getFruitName();
        this.attempts = isNull(fruitDto.getAttempts()) ? 1 : fruitDto.getAttempts();
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FruitModel{");
        sb.append("fruitName='").append(fruitName).append('\'');
        sb.append(", attempts=").append(attempts);
        sb.append('}');
        return sb.toString();
    }
}
