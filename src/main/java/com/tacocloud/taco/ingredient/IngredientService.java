package com.tacocloud.taco.ingredient;

import java.util.List;
import java.util.Map;

public interface IngredientService {

    Map<IngredientType, List<Ingredient>> getIngredientsGroupedByType();

    Ingredient findById(String id);

}
