package mwo.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Course course;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private FoodCategory foodCategory;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "recipe_id")
    private List<Quantity> quantityList;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "recipe_id")
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
