package com.example.aaachat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView back_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        back_about = (ImageView)findViewById(R.id.back_about);

        back_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUsActivity.this, buyerMainActivity.class));
                finish();
            }
        });
    }
}