package mwo.util;

import mwo.dto.QuantityDto;
import mwo.dto.RecipeStepDto;
import mwo.entity.Quantity;
import mwo.entity.RecipeStep;

import java.util.ArrayList;
import java.util.List;

public class RecipeConverter {

    public static List<RecipeStepDto> getRecipeStepDtoListFromRecipeStepList(List<RecipeStep> recipeStepList) {
        List<RecipeStepDto> recipeStepDtos = new ArrayList<>();

        recipeStepList.forEach(recipeStep -> {
            RecipeStepDto recipeStepDto = new RecipeStepDto();
            recipeStepDto.setId(recipeStep.getId());
            recipeStepDto.setStepDescription(recipeStep.getStepDescription());
            recipeStepDto.setStepNumber(recipeStep.getStepNumber());
            recipeStepDtos.add(recipeStepDto);
        });

        return recipeStepDtos;
    }

    public static List<QuantityDto> getQuantityDtoListFromQuantityList(List<Quantity> quantityList) {
        List<QuantityDto> quantityDtoList = new ArrayList<>();

        quantityList.forEach(quantity -> {
            QuantityDto quantityDto = new QuantityDto();
            quantityDto.setId(quantity.getId());
            quantityDto.setIngredient(quantity.getIngredient().getName());
            quantityDto.setIngredientQuantity(quantity.getIngredientQuantity());
            quantityDto.setUnitOfMeasurement(quantity.getUnitOfMeasurement().getName());
            quantityDtoList.add(quantityDto);
        });

        return quantityDtoList;
    }

}
