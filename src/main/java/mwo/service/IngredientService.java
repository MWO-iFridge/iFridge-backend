package mwo.service;

import java.util.List;

import mwo.entity.Ingredient;
import mwo.repository.IngredientRepository;

import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }
}
