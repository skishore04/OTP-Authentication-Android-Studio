package com.example.otpapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button logout = findViewById(R.id.buttonlog);
        logout.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finishAffinity();
        });
    }
}