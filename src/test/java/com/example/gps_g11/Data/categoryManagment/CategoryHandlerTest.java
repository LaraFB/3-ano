package com.example.gps_g11.Data.categoryManagment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryHandlerTest {

    @Test
    public void addCategoryWithName() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertTrue(categoryHandler.addCategory("TestCategory"));
        assertEquals("TestCategory", categoryHandler.getCategories().get(0).getName());
    }

    @Test
    public void addCategoryWithNameAndDescription() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertTrue(categoryHandler.addCategory("TestCategory", "Category for testing"));
        assertEquals(1, categoryHandler.getCategories().size());
        assertEquals("Category for testing", categoryHandler.getCategories().get(0).getDescription());
    }

    @Test
    public void addCategoryWithNameCollision() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("TestCategory");
        assertFalse(categoryHandler.addCategory("TestCategory"));
        assertEquals(1, categoryHandler.getCategories().size());
    }

    @Test
    public void removeCategoryObject() {
        CategoryHandler categoryHandler = new CategoryHandler();
        Category testCategory = new Category("TestCategory");
        categoryHandler.addCategory(testCategory);
        assertTrue(categoryHandler.removeCategory(testCategory));
        assertEquals(0, categoryHandler.getCategories().size());
    }

    @Test
    public void removeCategoryByIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("TestCategory");
        assertTrue(categoryHandler.removeCategory(0));
        assertEquals(0, categoryHandler.getCategories().size());
    }

    @Test
    public void removeCategoryByName() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("TestCategory");
        assertTrue(categoryHandler.removeCategory("TestCategory"));
        assertEquals(0, categoryHandler.getCategories().size());
    }

    @Test
    public void removeCategoryObjectWithNull() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertFalse(categoryHandler.removeCategory((Category)null));
    }

    @Test
    public void removeCategoryByInvalidIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertFalse(categoryHandler.removeCategory(1));
    }

    @Test
    public void removeCategoryByNullName() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertFalse(categoryHandler.removeCategory((String)null));
    }

    @Test
    public void editCategoryNameByName() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("OldName");
        assertTrue(categoryHandler.editCategoryName("OldName", "NewName"));
        assertEquals("NewName", categoryHandler.getCategoryName(0));
    }

    @Test
    public void editCategoryDescriptionByName() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("TestCategory");
        assertTrue(categoryHandler.editCategoryDescription("TestCategory", "New description"));
        assertEquals("New description", categoryHandler.getCategoryDescription(0));
    }

    @Test
    public void editCategoryNameByIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("OldName");
        assertTrue(categoryHandler.editCategoryName(0, "NewName"));
        assertEquals("NewName", categoryHandler.getCategoryName(0));
    }

    @Test
    public void editCategoryDescriptionByIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("TestCategory");
        assertTrue(categoryHandler.editCategoryDescription(0, "New description"));
        assertEquals("New description", categoryHandler.getCategoryDescription(0));
    }

    @Test
    public void editCategoryNameByInvalidIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertFalse(categoryHandler.editCategoryName(1, "NewName"));
    }

    @Test
    public void editCategoryDescriptionByInvalidIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertFalse(categoryHandler.editCategoryDescription(1, "New description"));
    }

    @Test
    public void editCategoryNameWithNullValues() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertFalse(categoryHandler.editCategoryName(null, "NewName"));
    }

    @Test
    public void editCategoryDescriptionWithNullValues() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertFalse(categoryHandler.editCategoryDescription("TestCategory", null));
    }

    @Test
    public void getCategoryByIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("TestCategory");
        Category retrievedCategory = categoryHandler.getCategory(0);
        assertNotNull(retrievedCategory);
        assertEquals("TestCategory", retrievedCategory.getName());
    }

    @Test
    public void getCategoryNameByIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("TestCategory");
        String categoryName = categoryHandler.getCategoryName(0);
        assertNotNull(categoryName);
        assertEquals("TestCategory", categoryName);
    }

    @Test
    public void getCategoryDescriptionByIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.addCategory("TestCategory", "Category for testing");
        String categoryDescription = categoryHandler.getCategoryDescription(0);
        assertNotNull(categoryDescription);
        assertEquals("Category for testing", categoryDescription);
    }

    @Test
    public void getCategoryByInvalidIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertNull(categoryHandler.getCategory(1));
    }

    @Test
    public void getCategoryNameByInvalidIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertNull(categoryHandler.getCategoryName(1));
    }

    @Test
    public void getCategoryDescriptionByInvalidIndex() {
        CategoryHandler categoryHandler = new CategoryHandler();
        assertNull(categoryHandler.getCategoryDescription(1));
    }
}