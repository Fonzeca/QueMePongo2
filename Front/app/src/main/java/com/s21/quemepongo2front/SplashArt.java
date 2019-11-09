package com.s21.quemepongo2front;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashArt extends AppCompatActivity {

    private static int splasArtTimeOut= 1000;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_art);
        new Handler().postDelayed(new Runnable() {

            public void run() {
                Intent gologin = new Intent(SplashArt.this,LoginActivity.class);
                startActivity(gologin);
                finish();
            }
        },splasArtTimeOut);

    }
}
