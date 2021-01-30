package com.example.aaachat.startup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaachat.R;
import com.example.aaachat.buyerMainActivity;

public class TermsAndConditionsActivity extends AppCompatActivity {

    private ImageView back_terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        back_terms = (ImageView)findViewById(R.id.back_terms);

        back_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TermsAndConditionsActivity.this, buyerMainActivity.class));
                finish();
            }
        });
    }
}