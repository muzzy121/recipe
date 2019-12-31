package com.muzzy.recipe.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;
    @Lob //For big strings in Database
    private String recipeNotes;
}
