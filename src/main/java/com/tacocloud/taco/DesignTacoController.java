package com.tacocloud.taco;

import com.tacocloud.order.OrderDto;
import com.tacocloud.taco.ingredient.Ingredient;
import com.tacocloud.taco.ingredient.IngredientDto;
import com.tacocloud.taco.ingredient.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

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
        model.addAllAttributes(getIngredientsGroupedByType());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid TacoDto design, Errors errors,
                                Model model, @ModelAttribute("order") OrderDto order) {

        if (errors.hasErrors()) {
            model.addAllAttributes(getIngredientsGroupedByType());
            return "design";
        }

        Taco taco = mapToTaco(design);
        Taco saved = tacoService.save(taco);
        order.addTaco(saved.getId());

        return "redirect:/orders/current";
    }

    private Map<String, List<IngredientDto>> getIngredientsGroupedByType() {
        return ingredientService.getAllIngredients()
                                .stream()
                                .map(ingredient -> new IngredientDto(ingredient.getId(), ingredient.getName(), ingredient.getType()))
                                .collect(groupingBy(ingredient -> ingredient.type().toString().toLowerCase()));
    }

    private Taco mapToTaco(TacoDto design) {
        List<Ingredient> ingredients =
                design.getIngredients()
                      .stream()
                      .map(ingredientService::findById)
                      .flatMap(Optional::stream)
                      .toList();

        Taco taco = new Taco();
        taco.setName(design.getName());
        taco.setIngredients(ingredients);
        return taco;
    }

}
