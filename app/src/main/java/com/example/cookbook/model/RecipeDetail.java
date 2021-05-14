package com.example.cookbook.model;

import java.util.ArrayList;

public class RecipeDetail {

    private String id, title, imageUrl, publisher, sourceUrl;
    ArrayList<Ingredients> ingredients = new ArrayList<>();

    public RecipeDetail(String id, String title, String imageUrl, String publisher, String sourceUrl, ArrayList<Ingredients> ingredients) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.publisher = publisher;
        this.sourceUrl = sourceUrl;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
