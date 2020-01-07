package com.muzzy.recipe.bootstrap;

import com.muzzy.recipe.domain.*;
import com.muzzy.recipe.repository.CategoryRepository;
import com.muzzy.recipe.repository.RecipeRepository;
import com.muzzy.recipe.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public void AddData() {
        Recipe recipe = new Recipe();
        if(categoryRepository.findByDescription("Mexican").isPresent()){
            Category category = categoryRepository.findByDescription("Mexican").get();
            recipe.getCategories().add(category);
//            category.getRecipes().add(recipe);
        } else {
            throw new RuntimeException("Category not found!");
        }
        if(categoryRepository.findByDescription("American").isPresent()){
            Category category = categoryRepository.findByDescription("American").get();
            recipe.getCategories().add(category);
//            category.getRecipes().add(recipe); -> now I dont know how to add recipes to existing entities

        } else {
            throw new RuntimeException("Category not found!");
        }

        recipe.addIngredients(newIngredient("Pcs.", new BigDecimal(2), "Avocado"));
        recipe.addIngredients(newIngredient("Tablespoon", new BigDecimal(2), "Sliced green onion"));
        recipe.addIngredients(newIngredient( "Pcs.", new BigDecimal(4), "Tortilla chips"));
        recipe.addIngredients(newIngredient( "Dash", new BigDecimal(1), "Peeper"));
        recipe.setCookTime(20);
        recipe.setPrepTime(20);
        recipe.setServings(4);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setDescription("Guacamole");
        recipe.setDirections("Cook as you see in cook time in owen, Buon Apetit!");

        Notes notes = new Notes();
        notes.setRecipeNotes("Some additional information about recipe");
        recipe.addNote(notes);

        recipeRepository.save(recipe);

        Recipe recipe2 = new Recipe();
        if(categoryRepository.findByDescription("Mexican").isPresent()){
            Category category = categoryRepository.findByDescription("Mexican").get();
            recipe2.getCategories().add(category);
//            category.getRecipes().add(recipe);
        } else {
            throw new RuntimeException("Category not found!");
        }
        if(categoryRepository.findByDescription("Fast Food").isPresent()){
            Category category = categoryRepository.findByDescription("Fast Food").get();
            recipe2.getCategories().add(category);
//            category.getRecipes().add(recipe); -> now I dont know how to add recipes to existing entities
        } else {
            throw new RuntimeException("Category not found!");
        }
        recipe2.addIngredients(newIngredient("Dash", new BigDecimal(1), "Ancho Chili Powder"));
        recipe2.addIngredients(newIngredient("Tablespoon", new BigDecimal(2), "Dried Oregano"));
        recipe2.addIngredients(newIngredient( "Pinch", new BigDecimal(.5), "Dried Cumin"));
        recipe2.addIngredients(newIngredient( "Pcs.", new BigDecimal(1), "Chicken"));
        recipe2.setCookTime(40);
        recipe2.setPrepTime(10);
        recipe2.setServings(2);
        recipe2.setDifficulty(Difficulty.MODERATE);
        recipe2.setDescription("Roasted Chicken");
        recipe2.setDirections("Add all ingredients, cook as you see in cook time, then done");

        notes = new Notes();
        notes.setRecipeNotes("Extra information about recipe");
        recipe.addNote(notes);

        recipeRepository.save(recipe2);
//  To test if I have an access from category to recipes - because in learning I don't see where adds recipes to category
//        categoryRepository.findAll().forEach(x -> x.getRecipes().forEach(y -> System.out.println(y.getPrepTime())));
//        categoryRepository.findAll().forEach(x -> System.out.println(x.getRecipes().size()));
    }


    private Ingredient newIngredient(String uom, BigDecimal amount, String name) {

        if (unitOfMeasureRepository.findByDescription(uom).isPresent()) {
//            System.out.println(unitOfMeasureRepository.findByDescription(uom).get());
            Ingredient ingredient = new Ingredient();
            ingredient.setAmount(amount);
            ingredient.setName(name);
            ingredient.setUom(unitOfMeasureRepository.findByDescription(uom).get());
//            System.out.println(ingredient.hashCode());
            return ingredient;
        } else throw new RuntimeException("Unit not found!");
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        AddData();
    }
}
