package mwo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private FoodCategory foodCategory;

    @OneToMany(mappedBy = "recipe")
    private List<Quantity> quantityList;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeStep> recipeStepList;

    @NotNull
    private String recipeName;

    @NotNull
    private String recipeDescription;

    @NotNull
    private Integer prepTime;

    @NotNull
    private long kcal;
}
