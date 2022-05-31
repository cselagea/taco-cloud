package com.tacocloud.taco.ingredient;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Map<IngredientType, List<Ingredient>> getIngredientsGroupedByType() {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        return StreamSupport.stream(ingredients.spliterator(), false)
                            .collect(groupingBy(Ingredient::type));

    }

    @Override
    public Ingredient findById(String id) {
        return ingredientRepository.findOne(id);
    }

}
