package mwo.service;

import mwo.entity.Course;
import mwo.entity.Quantity;
import mwo.entity.Recipe;
import mwo.entity.Ingredient;
import mwo.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).get();
    }

    public List<Recipe> getRecipeByCourse(Course course, List<Recipe> recipes){
        for(Recipe recipe : recipes){
            if(!Objects.equals(recipe.getCourseId(), course.getId())){
                recipes.remove(recipe);
            }
        }
        return recipes;
    }
}
