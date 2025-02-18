package com.example.chennai360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodMainActivity extends AppCompatActivity implements FoodRecyclerViewInterface {


    ArrayList<FoodModal> foodModals = new ArrayList<>();
    int[] foodImage = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4, R.drawable.food5, R.drawable.food6,
            R.drawable.food7, R.drawable.food8, R.drawable.food9, R.drawable.food10, R.drawable.food11, R.drawable.food12,
            R.drawable.food13, R.drawable.food14, R.drawable.food15, R.drawable.food16, R.drawable.food17, R.drawable.food,
            R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food};
    int[] ratingImage = {R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,
            R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,
            R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,
            R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,R.drawable.star_2,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);
        RecyclerView recyclerView = findViewById(R.id.food_recycler);
        setUpModals();
        foodAdaptor adaptor = new foodAdaptor(this, foodModals, this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpModals() {
        String[] names = getResources().getStringArray(R.array.famous_food_chennai);
        String[] calories = getResources().getStringArray(R.array.food_calories);
        String[] description = getResources().getStringArray(R.array.food_description);
        for(int i=0 ; i < names.length; i++) {
            foodModals.add(new FoodModal(names[i], calories[i],foodImage[i], ratingImage[i], description[i]));
        }
    }

    @Override
    public void onItemCLick(int position) {
        Intent intent = new Intent(FoodMainActivity.this, FoodMainActivity2.class);
        intent.putExtra("Name", foodModals.get(position).getName());
        intent.putExtra("cal", foodModals.get(position).getCalories());
        intent.putExtra("image", foodModals.get(position).getFoodImage());
        intent.putExtra("rating", foodModals.get(position).getRating());
        intent.putExtra("dis", foodModals.get(position).getDescription());
        startActivity(intent);

    }
}