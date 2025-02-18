package com.example.chennai360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    RelativeLayout destination, hotels, food;
    TextView textView;
    boolean doubleTap= false;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        textView=findViewById(R.id.textView);
        toolbar=findViewById(R.id.toolbar);
        destination = findViewById(R.id.destination);
        hotels = findViewById(R.id.hotels);
        food = findViewById(R.id.food);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FoodMainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//        else {
//            super.onBackPressed();
//        }

        if(doubleTap) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Press back again to exit the app", Toast.LENGTH_SHORT).show();
            doubleTap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTap = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        if((R.id.nav_switch_account) == id) {
            Intent intent = new Intent(getApplication(), LoginActivity.class);
            startActivity(intent);
        } else if((R.id.nav_logout) == id) {
//            auth.signOut();
//            Intent i=new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(i);
//            Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
//            finish();
        } else if((R.id.nav_home) == id){
            Intent intent = new Intent(getApplication(), NoteMainActivity.class);
            startActivity(intent);
        } else if (R.id.nav_cycle == id) {
            Intent intent = new Intent(getApplication(), BagMainActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}