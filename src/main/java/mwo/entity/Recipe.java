package mwo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String recipeName;

    private Long courseId;
    private Long foodCategoryId;
    @NotNull
    private String recipeDescription;
    private Float prepTime;
    private Long kcal;
}
