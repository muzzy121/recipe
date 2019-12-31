package com.muzzy.recipe.domain;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Pawel Mazur on 31/12/2019
 *
 */
@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    @Lob //as in string - if we need to create "BIG" fields in Database
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL) // if I will delete recipe I need to delete Notes to that recipe to!
    private Notes notes;

//    private Set<Category> categories;
}
