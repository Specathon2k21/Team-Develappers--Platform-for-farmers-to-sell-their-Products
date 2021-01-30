package com.example.aaachat.startup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.aaachat.R;
import com.example.aaachat.auth.Phoneno_verify;

public class WelcomeScreenActivity extends AppCompatActivity {

    AppCompatButton btnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        btnAgree = findViewById(R.id.btn_agree);

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, Phoneno_verify.class));
            }
        });
    }
}