package com.tacocloud.taco.ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> getAllIngredients();

    Ingredient findById(String id);

}
