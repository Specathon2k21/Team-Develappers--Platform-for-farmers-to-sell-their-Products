package com.example.aaachat.startup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaachat.R;
import com.example.aaachat.buyerMainActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageView back_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        back_profile = (ImageView)findViewById(R.id.back_profile);

        back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, buyerMainActivity.class));
                finish();
            }
        });
    }
}