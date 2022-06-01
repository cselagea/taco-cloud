package com.tacocloud.taco.ingredient;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("jpa")
public interface JpaIngredientRepository
        extends IngredientRepository, CrudRepository<Ingredient, String> {
}
