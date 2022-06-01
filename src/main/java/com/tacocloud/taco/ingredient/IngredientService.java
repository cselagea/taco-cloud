package com.tacocloud.taco.ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<Ingredient> getAllIngredients();

    Optional<Ingredient> findById(String id);

}
