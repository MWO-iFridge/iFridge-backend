package mwo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecipeStepDto implements Serializable {
    private long id;
    private long stepNumber;
    private String stepDescription;
}
