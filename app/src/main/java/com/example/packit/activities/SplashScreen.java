package com.example.packit.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.packit.R;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // Splash screen duration in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Start a background thread to perform any long-running initialization tasks
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SPLASH_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Once the background work is complete, start the main activity

                Intent intent = new Intent(SplashScreen.this, MainActivity.class);

                startActivity(intent);
                finish();
            }
        });
        backgroundThread.start();
    }
}
