package com.tacocloud.taco;

import com.tacocloud.taco.ingredient.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private final SimpleJdbcInsert tacoInsert;
    private final SimpleJdbcInsert tacoIngredientInsert;

    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.tacoInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tacos")
                .usingGeneratedKeyColumns("id");

        this.tacoIngredientInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tacos_ingredients");
    }

    @Override
    public Taco save(Taco taco) {
        final Long tacoId = saveTacoDetails(taco);
        taco.setId(tacoId);

        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }

        return taco;
    }

    private Long saveTacoDetails(Taco taco) {
        Map<String, ?> values = Map.ofEntries(
                Map.entry("name", taco.getName()),
                Map.entry("created_at", taco.getCreatedAt())
        );
        return tacoInsert.executeAndReturnKey(values).longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, Long tacoId) {
        Map<String, ?> values = Map.ofEntries(
                Map.entry("taco_id", tacoId),
                Map.entry("ingredient_id", ingredient.getId())
        );
        tacoIngredientInsert.execute(values);
    }

}
