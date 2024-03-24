package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.Objects;

public class Home extends AppCompatActivity {

    Button btnSignOut, btnPageOne, btnPageTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sPref.edit();
        sPref.getBoolean("isLogin", true);

        btnPageTwo.setOnClickListener(view ->{
            Intent intent = new Intent(Home.this,
                    Instagram.class);
            startActivity(intent);
        });

        btnSignOut.setOnClickListener(view -> {
            editor1.putBoolean("isLogin", false);
            editor1.apply();

            Toast.makeText(Home.this, "Signing Out", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Home.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void init()
    {
        btnSignOut = findViewById(R.id.btnSignOut);
        btnPageOne = findViewById(R.id.btnPageOne);
        btnPageTwo = findViewById(R.id.btnPageTwo);
    }
}