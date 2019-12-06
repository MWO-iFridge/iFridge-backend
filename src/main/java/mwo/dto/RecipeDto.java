package mwo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecipeDto implements Serializable {

    private Long id;
    private String course;
    private String foodCategory;
    private String recipeName;
    private String recipeDescription;
    private Integer prepTime;
    private Long kcal;
}
