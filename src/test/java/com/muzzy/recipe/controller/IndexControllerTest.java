package com.muzzy.recipe.controller;

import com.muzzy.recipe.domain.Recipe;
import com.muzzy.recipe.service.RecipeService;
import com.muzzy.recipe.service.RecipeServiceImpl;
import com.sun.xml.internal.ws.resources.UtilMessages;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void indexPage() {
        //given
        String testIndex = "index";
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(new Recipe(1L));
        recipeSet.add(new Recipe(2L));
        when(recipeService.getRecipes()).thenReturn(recipeSet);

        //stores all data temporary container
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String result = indexController.indexPage(model);

        //then
        assertEquals(testIndex, result);
        verify(recipeService,times(1)).getRecipes();
        //Test how many times addAttr was called - 1. param has to be Matcher(eq - word means like match "recipes) anySet() means anything, any object
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());

        //take(captures) whats there in method is passed to recipes field and later allows to access to it outside of method and inside of test
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());


    }
}