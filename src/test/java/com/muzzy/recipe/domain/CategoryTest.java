package com.muzzy.recipe.domain;

import org.junit.Before;
import org.junit.Test;

import javax.naming.InsufficientResourcesException;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long testValue = 5L;
        category.setId(testValue);
        assertEquals(testValue,category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}