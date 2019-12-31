package com.muzzy.recipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Pawel Mazur
 */
@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private UnitOfMeasure uom;

}
