package com.muzzy.recipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
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
    @Lob
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob //as in string - if we need to create "BIG" fields in Database
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL) // if I will delete recipe I need to delete Notes to that recipe to!
    private Notes notes;

    @ManyToMany
    @JoinTable(name="recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();

    public void addNote(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }

    public Recipe addIngredients(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
