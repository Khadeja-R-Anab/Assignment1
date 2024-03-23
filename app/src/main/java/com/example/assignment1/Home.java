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

    FragmentManager manager;
    Button btnSignOut, btnPageOne, btnPageTwo;
    View pageOneView, pageTwoView;
    Fragment PageOneFrag, PageTwoFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sPref.edit();
        boolean flag = sPref.getBoolean("isLogin", true);

        btnPageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .hide(PageOneFrag)
                        .show(PageTwoFrag)
                        .commit();
            }
        });

        btnPageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .hide(PageTwoFrag)
                        .show(PageOneFrag)
                        .commit();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor1.putBoolean("isLogin", false);
                editor1.apply();

                Toast.makeText(Home.this, "Signing Out", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Home.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void init()
    {
        manager = getSupportFragmentManager();
        btnSignOut = findViewById(R.id.btnSignOut);
        btnPageOne = findViewById(R.id.btnPageOne);
        btnPageTwo = findViewById(R.id.btnPageTwo);

        pageOneView = Objects.requireNonNull(manager.findFragmentById(R.id.fragPageOne)).requireView();
        pageTwoView = Objects.requireNonNull(manager.findFragmentById(R.id.fragPageTwo)).requireView();

        PageOneFrag = manager.findFragmentById(R.id.fragPageOne);
        PageTwoFrag = manager.findFragmentById(R.id.fragPageTwo);
    }
}