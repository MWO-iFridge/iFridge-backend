package mwo.controller.rest;

import mwo.entity.Course;
import mwo.entity.Ingredient;
import mwo.entity.Recipe;
import mwo.service.CourseService;
import mwo.service.QuantityService;
import mwo.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    private RecipeService recipeService;
    private QuantityService quantityService;
    private CourseService courseService;

    private Random rand = new Random();


    public RecipeController(RecipeService recipeService, QuantityService quantityService, CourseService courseService) {
        this.recipeService = recipeService;
        this.quantityService = quantityService;
        this.courseService = courseService;
    }

    @GetMapping(value = "/recipe")
    private List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    private List<Recipe> getRecipeByGivenIngredients(List<Long> givenIngredients){
        List<Recipe> recipes = getAllRecipes();
        for(Recipe recipe : recipes){
            List<Long> ingredientIdsInRecipe = quantityService.getAllIngredientsFromRecipeByRecipeId(recipe.getId());
            if (givenIngredients.containsAll(ingredientIdsInRecipe)){
                    recipes.add(recipe);
            }
        }
        return recipes;
    }

    public List<List<Recipe>> getNutritionPlansForOneDay(Long kcal, List<Ingredient> ingredients) {
        Course dinner = courseService.getCourseById(Course.DINNER_ID).orElseGet(Course::new);
        Course breakfast = courseService.getCourseById(Course.BREAKFAST_ID).orElseGet(Course::new);
        Course supper = courseService.getCourseById(Course.SUPPER_ID).orElseGet(Course::new);
        List<Long> ingredientIdsInRecipe = ingredients
                .stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());
        List<Recipe> dinnerRecipes = chooseRecipesForGivenCourse(dinner, getRecipeByGivenIngredients(ingredientIdsInRecipe));
        for(Recipe recipe : dinnerRecipes){
            if(recipe.getKcal() >= recommendedKcalForDinner(kcal)){
                dinnerRecipes.remove(recipe);
            }
        }

        List<List<Recipe>> allOptions = new LinkedList<>();

        for(Recipe dinnerRecipe : dinnerRecipes){
            List<Recipe> option = new LinkedList<>();
            List<Long> ingredientsCopy = ingredientIdsInRecipe;
            option.add(dinnerRecipe);

            ingredientsCopy.removeAll(quantityService.getAllIngredientsFromRecipeByRecipeId(dinnerRecipe.getId()));

            List<Recipe> breakfastRecipes = chooseRecipesForGivenCourse(breakfast, getRecipeByGivenIngredients(ingredientsCopy));
            for(Recipe r : breakfastRecipes){
                if(r.getKcal() >= recommendedKcalForBreakfast(kcal)){
                    breakfastRecipes.remove(r);
                }
            }
            Recipe randomBreakfastRecipe = chooseRandomRecipe(breakfastRecipes);
            option.add(randomBreakfastRecipe);
            ingredientsCopy.removeAll(quantityService.getAllIngredientsFromRecipeByRecipeId(randomBreakfastRecipe.getId()));

            List<Recipe> supperRecipes = chooseRecipesForGivenCourse(supper, getRecipeByGivenIngredients(ingredientsCopy));
            for(Recipe r : supperRecipes){
                if(r.getKcal() >= recommendedKcalForSupper(kcal)){
                    supperRecipes.remove(r);
                }
            }
            Recipe randomSupperRecipes = chooseRandomRecipe(supperRecipes);
            option.add(randomSupperRecipes);
            allOptions.add(option);
        }

        return allOptions;
    }

    private Recipe chooseRandomRecipe(List<Recipe> recipes) {
        int randomIndex = rand.nextInt(recipes.size());
        return recipes.get(randomIndex);
    }

    @GetMapping(value = "/random")
    public Recipe chooseRandomRecipe() {
        return chooseRandomRecipe(getAllRecipes());
    }

    private Long recommendedKcalForDinner(Long kcal) {
        return kcal/2;
    }
    private Long recommendedKcalForBreakfast(Long kcal) {
        return kcal/4;
    }
    private Long recommendedKcalForSupper(Long kcal) {
        return kcal/4;
    }

    private List<Recipe> chooseRecipesForGivenCourse(Course course, List<Recipe> recipes) {
        for(Recipe recipe : recipes){
            if (!Objects.equals(recipe.getCourse().getId(), course.getId())){
                recipes.remove(recipe);
            }
        }
        return recipes;
    }
}
