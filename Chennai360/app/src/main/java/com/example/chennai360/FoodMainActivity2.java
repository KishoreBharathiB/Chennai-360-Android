package com.example.chennai360;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodMainActivity2 extends AppCompatActivity {
    TextView name, description;
    ImageView food, rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main2);


        String intentName = getIntent().getStringExtra("Name");
        int intentFoodImage = getIntent().getIntExtra("image",0);
        int intentRating = getIntent().getIntExtra("rating",0);
        String intentDescription = getIntent().getStringExtra("dis");

        name = findViewById(R.id.foodName);
        description = findViewById(R.id.description);
        food = findViewById(R.id.foodImage);
        rating = findViewById(R.id.rating);

        name.setText(intentName);
        description.setText(intentDescription);
        food.setImageResource(intentFoodImage);
        rating.setImageResource(intentRating);

    }
}