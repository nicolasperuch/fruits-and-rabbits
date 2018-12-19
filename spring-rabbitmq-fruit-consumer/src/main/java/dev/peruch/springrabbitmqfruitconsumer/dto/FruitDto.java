package dev.peruch.springrabbitmqfruitconsumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.io.Serializable;

public class FruitDto implements Serializable {

    private String fruitName;
    private Integer attempts;

    public FruitDto(@JsonProperty("fruitName") String fruitName,
                    @JsonProperty("attempts") Integer attempts) {
        this.fruitName = fruitName;
        this.attempts = attempts;
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
        return Objects.toStringHelper(this)
                .add("fruitName", fruitName)
                .add("attempts", attempts)
                .toString();
    }
}
