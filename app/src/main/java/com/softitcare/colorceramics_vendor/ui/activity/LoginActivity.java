package com.softitcare.colorceramics_vendor.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softitcare.colorceramics_vendor.MainActivity;
import com.softitcare.colorceramics_vendor.R;

public class LoginActivity extends AppCompatActivity {

    Button cirLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cirLoginButton = findViewById(R.id.cirLoginButton);

        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        });
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this,RegistrationActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}