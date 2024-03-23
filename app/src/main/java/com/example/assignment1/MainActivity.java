package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    View loginView, signupView;

    Button btnLogin, btnSignup, btnRSignup, btnRExit, btnRLogin;
    RadioButton rBtnMale, rBtnFemale;

    TextInputEditText etEmail, etPassword, etRFirstName, etRLastName, etRPassword, etRConfirmPassword, etREmail;

    Fragment LoginFragment, SignUpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        SharedPreferences.Editor editor1 = sPref.edit();


        boolean flag = sPref.getBoolean("isLogin", false);
        if(flag)
        {
            Intent intent = new Intent(MainActivity.this,
                    Home.class);
            startActivity(intent);
            finish();
        }


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .hide(LoginFragment)
                        .show(SignUpFragment)
                        .commit();
            }
        });

        btnRLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .show(LoginFragment)
                        .hide(SignUpFragment)
                        .commit();
            }
        });

        btnRSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FName = etRFirstName.getText().toString();
                String LName = etRLastName.getText().toString();
                String Pass = etRPassword.getText().toString();
                String ConfirmPass = etRConfirmPassword.getText().toString();
                String REmail = etREmail.getText().toString();
                if (!(FName.isEmpty()) && !(LName.isEmpty()) && !(Pass.isEmpty()) && !(ConfirmPass.isEmpty()) && !(REmail.isEmpty()) && (rBtnFemale.isChecked() || rBtnMale.isChecked())) {
                    if(Objects.requireNonNull(Pass.equals(ConfirmPass)))
                    {
                        editor.putString("email", Objects.requireNonNull(etREmail.getText()).toString());
                        editor.putString("password", Objects.requireNonNull(etRPassword.getText()).toString());
                        editor.apply();

                        Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                        editor1.putBoolean("isLogin", true);
                        editor1.apply();

                        Intent intent = new Intent(MainActivity.this, Home.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = sPref.getString("email", null);
                String password = sPref.getString("password", null);
                if(Objects.requireNonNull(etEmail.getText()).toString().equals(email)
                            && Objects.requireNonNull(etPassword.getText()).toString().equals(password))
                {
                    editor1.putBoolean("isLogin", true);
                    editor1.apply();

                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void init()
    {
        manager = getSupportFragmentManager();

        loginView = Objects.requireNonNull(manager.findFragmentById(R.id.fragLogin)).requireView();
        signupView = Objects.requireNonNull(manager.findFragmentById(R.id.fragSignup)).requireView();

        btnLogin = loginView.findViewById(R.id.btnLogin);
        btnSignup = loginView.findViewById(R.id.btnSignUp);
        btnRLogin = signupView.findViewById(R.id.btnRLogin);
        btnRSignup = signupView.findViewById(R.id.btnRSignup);
        btnRExit = signupView.findViewById(R.id.btnRExit);

        etEmail = loginView.findViewById(R.id.etEmail);
        etPassword = loginView.findViewById(R.id.etPassword);
        etRFirstName = signupView.findViewById(R.id.etRFirstName);
        etRLastName = signupView.findViewById(R.id.etRLastName);
        etRPassword = signupView.findViewById(R.id.etRPassword);
        etRConfirmPassword = signupView.findViewById(R.id.etRConfirmPassword);
        etREmail = signupView.findViewById(R.id.etREmail);

        rBtnFemale = signupView.findViewById(R.id.rBtnFemale);
        rBtnMale = signupView.findViewById(R.id.rBtnMale);

        LoginFragment = manager.findFragmentById(R.id.fragLogin);
        SignUpFragment = manager.findFragmentById(R.id.fragSignup);

    }
}