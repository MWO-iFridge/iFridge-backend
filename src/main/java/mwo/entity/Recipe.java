package mwo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Course course;

    private FoodCategory foodCategory;

    @NotNull
    private String recipeName;

    @NotNull
    private String recipeDescription;

    @NotNull
    private LocalTime prepTime;

    @NotNull
    private long kcal;
}
