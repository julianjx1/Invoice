package com.invoice.bd.view.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.invoice.bd.R;
import com.invoice.bd.databinding.ActivitySignupBinding;
import com.invoice.bd.view.MainActivity;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.btnSignUp.setOnClickListener(view -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }
}