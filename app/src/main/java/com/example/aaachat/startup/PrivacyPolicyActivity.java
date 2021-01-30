package com.example.aaachat.startup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaachat.R;
import com.example.aaachat.buyerMainActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private ImageView back_privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        back_privacy = (ImageView)findViewById(R.id.back_privacy);

        back_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrivacyPolicyActivity.this, buyerMainActivity.class));
            }
        });
    }
}