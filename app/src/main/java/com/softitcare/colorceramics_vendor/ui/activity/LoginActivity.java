package com.softitcare.colorceramics_vendor.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softitcare.colorceramics_vendor.R;
import com.softitcare.colorceramics_vendor.api.ApiUtils;
import com.softitcare.colorceramics_vendor.interfaces.LoginService;
import com.softitcare.colorceramics_vendor.model.ResObj;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    LoginService loginService;

    Button cirLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        changeStatusBarColor();

        cirLoginButton = findViewById(R.id.cirLoginButton);
        edtUsername = (EditText) findViewById(R.id.editTextEmail);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        loginService = ApiUtils.getLoginService();

        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                //validate form
                if(validateLogin(username, password)){
                    //do login
                    doLogin(username, password);
                }

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        });
    }
    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username,final String password){
        Call call = loginService.login(username,password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    ResObj resObj = (ResObj) response.body();
                    if(resObj.getMessage().equals("true")){
                        //login start main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this,RegistrationActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}