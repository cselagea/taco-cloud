package com.tacocloud.taco;

import com.tacocloud.order.OrderDto;
import com.tacocloud.taco.ingredient.Ingredient;
import com.tacocloud.taco.ingredient.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final TacoService tacoService;
    private final IngredientService ingredientService;

    public DesignTacoController(TacoService tacoService, IngredientService ingredientService) {
        this.tacoService = tacoService;
        this.ingredientService = ingredientService;
    }

    @ModelAttribute("order")
    public OrderDto order() {
        return new OrderDto();
    }

    @ModelAttribute("design")
//    @ModelAttribute("taco")
    public TacoDto taco() {
        return new TacoDto();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAllAttributes(getIngredientsGroupedByTypeString());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid TacoDto design, Errors errors,
                                Model model, @ModelAttribute("order") OrderDto order) {

        if (errors.hasErrors()) {
            model.addAllAttributes(getIngredientsGroupedByTypeString());
            return "design";
        }

        Taco taco = mapToTaco(design);
        Taco saved = tacoService.save(taco);
        order.addTaco(saved.getId());

        return "redirect:/orders/current";
    }

    private Map<String, List<Ingredient>> getIngredientsGroupedByTypeString() {
        // map keys to String
        return ingredientService.getIngredientsGroupedByType()
                                .entrySet()
                                .stream()
                                .collect(toMap(entry -> entry.getKey().toString().toLowerCase(),
                                        Map.Entry::getValue));
    }

    // TODO experiment with MapStruct
    private Taco mapToTaco(TacoDto design) {
        List<Ingredient> ingredients =
                design.getIngredients()
                      .stream()
                      .map(ingredientService::findById)
                      .toList();

        Taco taco = new Taco();
        taco.setName(design.getName());
        taco.setIngredients(ingredients);
        return taco;
    }

}
