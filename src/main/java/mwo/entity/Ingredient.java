package mwo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Ingredient {

    @Id
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 255)
    private String name;

}
