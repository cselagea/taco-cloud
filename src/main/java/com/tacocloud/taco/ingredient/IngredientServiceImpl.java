package com.tacocloud.taco.ingredient;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientRepository.findAll()) {
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    @Override
    public Ingredient findById(String id) {
        return ingredientRepository.findOne(id);
    }

}
