package com.example.aaachat.startup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaachat.R;
import com.example.aaachat.buyerMainActivity;
import com.example.aaachat.seller.sellerProfileActivity;

public class HomeActivity extends AppCompatActivity {

    private ImageView home_to_buyer;
    private Button seller_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        home_to_buyer = (ImageView)findViewById(R.id.home_to_buyer);
        seller_button = (Button)findViewById(R.id.seller_button);

        home_to_buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, buyerMainActivity.class));
            }
        });

        seller_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, sellerProfileActivity.class));
            }
        });
    }
}