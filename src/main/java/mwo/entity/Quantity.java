package mwo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Reprezentuje konkretny skladnik w danej ilości np. 2ml mleka dla przepisu np. jajecznicy
 */
@Entity
@Data
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "ingredient_uom_id")
    private UnitOfMeasurement unitOfMeasurement;

    @NotNull
    private Integer ingredientQuantity;
}
