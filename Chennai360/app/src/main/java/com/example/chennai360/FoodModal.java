package com.example.chennai360;

public class FoodModal {
    String name;
    String calories;
    String description;
    int foodImage;
    int rating;


    public FoodModal(String name, String calories, int foodImage, int rating, String description) {
        this.name = name;
        this.calories = calories;
        this.foodImage = foodImage;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCalories() {
        return calories;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }
}

