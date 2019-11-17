package mwo.service;

import mwo.entity.Course;
import mwo.entity.Recipe;
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

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
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
