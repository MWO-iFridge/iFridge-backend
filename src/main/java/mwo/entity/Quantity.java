package mwo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long recipeId;

    @NotNull
    private Long ingredientId;

    @NotNull
    private Long ingredientUomId;

    @NotNull
    private Float ingredientQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Long getIngredientUomId() {
        return ingredientUomId;
    }

    public void setIngredientUomId(Long ingredientUomId) {
        this.ingredientUomId = ingredientUomId;
    }

    public Float getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Float ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }
}
