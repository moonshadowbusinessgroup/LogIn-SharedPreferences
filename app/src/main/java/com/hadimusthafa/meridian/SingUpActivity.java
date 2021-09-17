package com.hadimusthafa.meridian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SingUpActivity extends AppCompatActivity implements Validation {

    EditText username, password, repassword, email;
    Button register;
    TextView already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        username = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        repassword = findViewById(R.id.conPass);
        register = findViewById(R.id.singUp);
        already = findViewById(R.id.already);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        });
    }

    private void saveData() {

        String uname = username.getText().toString();
        String eml = email.getText().toString();
        String pwd = password.getText().toString();
        String repwd = repassword.getText().toString();

        if (isNotNull()) {

            if (!uname.isEmpty() && !eml.isEmpty() && !eml.isEmpty() && !pwd.isEmpty()) {
                if (pwd.equals(repwd)) {
                    if (pwd.length() >= 8) {

                        if (checkEmail(eml)) {
                            email.setError("user already exist!");
                            email.requestFocus();
                        } else if (!checkPass(pwd, repwd)) {
                            password.setError("password doesn't match");
                        } else {
                            Preferences.setRegisteredUser(getBaseContext(), uname);
                            Preferences.setRegisteredEmail(getBaseContext(), eml);
                            Preferences.setRegisteredPass(getBaseContext(), pwd);
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SingUpActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                    } else {
                        Toast.makeText(SingUpActivity.this, "Password must to be 8 characters or more!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SingUpActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SingUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void input(EditText txt, String s) {
        txt.setError(" Please enter" + " " + s);
        txt.requestFocus();
    }

    @Override
    public boolean isNotNull() {
        if (username.getText().toString().isEmpty()) {
            input(username, "username");
            return false;
        }
        if (email.getText().toString().isEmpty()) {
            input(email, "email");
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            input(password, "password");
            return false;
        }

        if (!(password.getText().toString().length() >= 8)) {
            password.setError("Password must to be 8 characters or more!");
            return false;
        }

        if (repassword.getText().toString().isEmpty()) {
            input(repassword, "confirm password");
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email) {
        return email.equals(Preferences.getRegisteredEmail(getBaseContext()));
    }

    public boolean checkPass(String password, String repassword) {
        return password.equals(repassword);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

