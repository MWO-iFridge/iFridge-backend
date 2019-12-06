package mwo.entity;

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

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "ingredient_uom_id")
    private UnitOfMeasurement unitOfMeasurement;

    @NotNull
    private Integer ingredientQuantity;
}
