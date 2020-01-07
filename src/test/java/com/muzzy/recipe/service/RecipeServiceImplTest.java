package com.muzzy.recipe.service;

import com.muzzy.recipe.domain.Recipe;
import com.muzzy.recipe.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeServiceImpl;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        //Create new recipe and add it to recipes set
        Recipe recipe = new Recipe();
        HashSet<Recipe> mockRecipes = new HashSet<>();
        mockRecipes.add(recipe);

        // Set up mock - when recipeServiceImpl calls getRecipes() - if works properly,
        // should call repository findAll() method, which in this case we will mock
        when(recipeRepository.findAll()).thenReturn(mockRecipes);

        //now we calls getRecipes()
        Set<Recipe> recipeSet = recipeServiceImpl.getRecipes();

        //Test
        assertEquals(recipeSet.size(), 1 );
        //Verify how many times recipe repository was asked for data ( in this case method findAll() was called)
        verify(recipeRepository,times(1)).findAll();
    }
}