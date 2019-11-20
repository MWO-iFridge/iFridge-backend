package mwo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Course {

    public static final Long DINNER_ID = 1L;
    public static final Long BREAKFAST_ID = 2L;
    public static final Long SUPPER_ID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;
}
