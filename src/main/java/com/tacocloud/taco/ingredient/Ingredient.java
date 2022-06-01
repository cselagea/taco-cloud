package com.tacocloud.taco.ingredient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true) // for JPA
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    private final String id;

    private final String name;

    @Enumerated(EnumType.STRING)
    private final IngredientType type;

}
