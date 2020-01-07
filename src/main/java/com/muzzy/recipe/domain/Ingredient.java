package com.muzzy.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Pawel Mazur
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER) //load every time its default but it is here to show how to use fetch type
    private UnitOfMeasure uom;

}
