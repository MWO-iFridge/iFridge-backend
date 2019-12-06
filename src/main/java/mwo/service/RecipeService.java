package mwo.service;

import mwo.entity.Course;
import mwo.entity.FoodCategory;
import mwo.entity.Ingredient;
import mwo.entity.Recipe;
import mwo.entity.UnitOfMeasurement;
import mwo.repository.FoodCategoryRepository;
import mwo.repository.IngredientRepository;
import mwo.repository.RecipeRepository;
import mwo.repository.UnitOfMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UnitOfMeasurementRepository uomRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe addNewRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getRecipeByCourse(Course course, List<Recipe> recipes){
        for(Recipe recipe : recipes){
            if(!Objects.equals(recipe.getCourse().getId(), course.getId())){
                recipes.remove(recipe);
            }
        }
        return recipes;
    }

    public FoodCategory findFoodCategoryByName(String name) {
        return foodCategoryRepository.findFoodCategoryByName(name);
    }

    public Ingredient findIngredientByName(String name) {
        return ingredientRepository.findIngredientByName(name);
    }

    public UnitOfMeasurement findUnitOfMeasurementByName(String name) {
        return uomRepository.findUnitOfMeasurementByName(name);
    }
}
