package com.hadimusthafa.meridian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements Validation {
    EditText email, password;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.notAcc);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotNull()) {
                    login();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SingUpActivity.class));
            }
        });
    }

    private void login() {

        String eml = email.getText().toString();
        String pwd = password.getText().toString();

        if (!checkEmail(eml) || !checkPass(pwd)) {
//            Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
            if (checkEmail(eml) && !checkPass(pwd)) {
                password.setError("Enter Correct Password");
            } else if (!checkEmail(eml)) {
                email.setError("Enter Valid Email");
            }
        } else {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            finishAffinity();
            Toast.makeText(this, "Welcome back.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void input(EditText txt, String s) {
        txt.setError("Enter valid" + " " + s);
        txt.requestFocus();
    }

    @Override
    public boolean isNotNull() {
        if (email.getText().toString().isEmpty()) {
            input(email, "email");
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            input(password, "Password");
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email) {
        return email.equals(Preferences.getRegisteredEmail(getBaseContext()));
    }

    public boolean checkPass(String password) {
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
