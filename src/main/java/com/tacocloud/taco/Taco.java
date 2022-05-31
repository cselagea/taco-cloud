package com.tacocloud.taco;

import com.tacocloud.taco.ingredient.Ingredient;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class Taco {

    private Long id;
    private Instant createdAt;
    private String name;
    private List<Ingredient> ingredients = new ArrayList<>();

}
