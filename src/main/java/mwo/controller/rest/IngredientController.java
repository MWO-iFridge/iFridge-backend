package mwo.controller.rest;

import java.util.List;

import mwo.entity.Ingredient;
import mwo.service.IngredientService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/ingredient")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

}
