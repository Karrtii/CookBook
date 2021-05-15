package com.example.cookbook.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipeTable")
public class AddRecipe {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String ingredients;
    private String steps;

    public AddRecipe(String title, String ingredients, String steps) {
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
