package com.example.gps_g11.Data.categoryManagment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void constructorWithName() {
        Category category = new Category("TestCategory");
        assertEquals("TestCategory", category.getName());
        assertEquals("", category.getDescription());
    }

    @Test
    public void constructorWithNameAndDescription() {
        Category category = new Category("TestCategory", "Category for testing");
        assertEquals("TestCategory", category.getName());
        assertEquals("Category for testing", category.getDescription());
    }

    @Test
    public void constructorWithNullName() {
        Category category = new Category(null);
        assertEquals("", category.getName());
        assertEquals("", category.getDescription());
    }

    @Test
    public void constructorWithNullDescription() {
        Category category = new Category("TestCategory", null);
        assertEquals("TestCategory", category.getName());
        assertEquals("", category.getDescription());
    }

    @Test
    public void setName() {
        Category category = new Category("TestCategory");
        category.setName("NewCategory");
        assertEquals("NewCategory", category.getName());
    }

    @Test
    public void setNameWithNull() {
        Category category = new Category("TestCategory");
        category.setName(null);
        assertEquals("TestCategory", category.getName());
    }

    @Test
    public void setDescription() {
        Category category = new Category("TestCategory");
        category.setDescription("New description");
        assertEquals("New description", category.getDescription());
    }

    @Test
    public void setDescriptionWithNull() {
        Category category = new Category("TestCategory");
        category.setDescription(null);
        assertEquals("", category.getDescription());
    }
}