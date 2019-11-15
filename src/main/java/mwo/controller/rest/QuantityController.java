package mwo.controller.rest;

import mwo.entity.Ingredient;
import mwo.entity.Quantity;
import mwo.entity.Recipe;
import mwo.service.QuantityService;
import mwo.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuantityController {

    private QuantityService quantityService;

    public QuantityController(QuantityService quantityService) {
        this.quantityService = quantityService;
    }

    @GetMapping
    public List<Quantity> getAllQuantities() {
        return quantityService.getAllQuantities();
    }

}
