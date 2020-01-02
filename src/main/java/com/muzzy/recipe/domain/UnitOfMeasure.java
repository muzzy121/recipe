package com.muzzy.recipe.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "unit_of_measure")
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;
}
