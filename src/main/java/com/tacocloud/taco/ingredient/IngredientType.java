package com.tacocloud.taco.ingredient;

import java.util.Arrays;

public enum IngredientType {

    WRAP,
    PROTEIN,
    VEGGIES,
    CHEESE,
    SAUCE;

    public static IngredientType fromString(String value) {
        return Arrays.stream(IngredientType.values())
                     .filter(type -> type.toString().equalsIgnoreCase(value))
                     .findAny()
                     .orElseThrow(IllegalArgumentException::new);
    }

}
