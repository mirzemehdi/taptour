package com.mmk.taptour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginRegisterActivity extends AppCompatActivity {
    private Button loginButton,registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginregister);

        setup();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passToLogin=new Intent(LoginRegisterActivity.this,LoginActivity.class);
                startActivity(passToLogin);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void setup() {
        loginButton=(Button)findViewById(R.id.loginWithEmailButton);
        registerButton=(Button)findViewById(R.id.registerButton);
    }
}
