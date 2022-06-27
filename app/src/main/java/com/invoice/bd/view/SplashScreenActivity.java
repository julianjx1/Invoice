package com.invoice.bd.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;

import com.invoice.bd.R;
import com.invoice.bd.view.authentication.LoginActivity;


public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            finish();
        }, 2500);

    }


}