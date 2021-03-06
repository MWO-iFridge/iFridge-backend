package mwo.controller.rest;

import mwo.dto.RecipeDto;
import mwo.entity.Course;
import mwo.entity.FoodCategory;
import mwo.entity.Ingredient;
import mwo.entity.Quantity;
import mwo.entity.Recipe;
import mwo.entity.RecipeStep;
import mwo.entity.UnitOfMeasurement;
import mwo.service.CourseService;
import mwo.service.QuantityService;
import mwo.service.RecipeService;
import mwo.util.RecipeConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @PostMapping(value = "/recipe")
    private void addRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setKcal(recipeDto.getKcal());
        recipe.setPrepTime(recipeDto.getPrepTime());
        recipe.setRecipeName(recipeDto.getRecipeName());
        recipe.setRecipeDescription(recipeDto.getRecipeDescription());

        Course course = courseService.getCourseByName(recipeDto.getCourse());
        if(course == null) {
            course = new Course();
            course.setName(recipeDto.getCourse());
        }
        recipe.setCourse(course);

        FoodCategory foodCategory = recipeService.findFoodCategoryByName(recipeDto.getFoodCategory());
        if(foodCategory == null) {
            foodCategory = new FoodCategory();
            foodCategory.setName(recipeDto.getFoodCategory());
        }
        recipe.setFoodCategory(foodCategory);

        List<Quantity> quantityList = new ArrayList<>();
        if(recipeDto.getQuantityDtoList() != null) {
            recipeDto.getQuantityDtoList().forEach(quantityDto -> {
                Quantity quantity = new Quantity();

                Ingredient ingredient = recipeService.findIngredientByName(quantityDto.getIngredient());
                if (ingredient == null) {
                    ingredient = new Ingredient();
                    ingredient.setName(quantityDto.getIngredient());
                }

                quantity.setIngredient(ingredient);
                quantity.setIngredientQuantity(quantityDto.getIngredientQuantity());

                UnitOfMeasurement uom = recipeService.findUnitOfMeasurementByName(quantityDto.getUnitOfMeasurement());
                if (uom == null) {
                    uom = new UnitOfMeasurement();
                    uom.setName(quantityDto.getUnitOfMeasurement());
                }
                quantity.setUnitOfMeasurement(uom);
                quantityList.add(quantity);
            });
            recipe.setQuantityList(quantityList);
        }

        if(recipeDto.getRecipeStepDtoList() != null) {
            List<RecipeStep> recipeStepList = new ArrayList<>();
            recipeDto.getRecipeStepDtoList().forEach(recipeStepDto -> {
                RecipeStep recipeStep = new RecipeStep();
                recipeStep.setStepDescription(recipeStepDto.getStepDescription());
                recipeStep.setStepNumber(recipeStepDto.getStepNumber());
                recipeStepList.add(recipeStep);
            });
            recipe.setRecipeStepList(recipeStepList);
        }

        recipeService.addNewRecipe(recipe);
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
    public RecipeDto chooseRandomRecipe() {
        RecipeDto recipeDto = new RecipeDto();
        Recipe recipe = chooseRandomRecipe(getAllRecipes());

        recipeDto.setId(recipe.getId());
        recipeDto.setCourse(recipe.getCourse().getName());
        recipeDto.setFoodCategory(recipe.getFoodCategory().getName());
        recipeDto.setKcal(recipe.getKcal());
        recipeDto.setPrepTime(recipe.getPrepTime());
        recipeDto.setRecipeDescription(recipe.getRecipeDescription());
        recipeDto.setRecipeName(recipe.getRecipeName());
        recipeDto.setQuantityDtoList(RecipeConverter.getQuantityDtoListFromQuantityList(recipe.getQuantityList()));
        recipeDto.setRecipeStepDtoList(RecipeConverter.getRecipeStepDtoListFromRecipeStepList(recipe.getRecipeStepList()));

        return recipeDto;
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
