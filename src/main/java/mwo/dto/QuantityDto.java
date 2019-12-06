package mwo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuantityDto implements Serializable {
    private long id;
    private String ingredient;
    private String unitOfMeasurement;
    private Integer ingredientQuantity;
}
