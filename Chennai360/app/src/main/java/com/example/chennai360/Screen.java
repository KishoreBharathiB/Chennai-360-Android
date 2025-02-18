package com.example.chennai360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class Screen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 2800;
    TextView title, devText;
    ImageView screenImage;
    Animation top, bottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        screenImage = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        devText = findViewById(R.id.developerText);

        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);

        screenImage.setAnimation(top);
        title.setAnimation(bottom);
        devText.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Screen.this, sample.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}