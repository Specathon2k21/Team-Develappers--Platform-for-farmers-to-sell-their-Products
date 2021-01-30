package com.example.aaachat.seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aaachat.R;
import com.example.aaachat.startup.HomeActivity;
import com.example.aaachat.startup.Seller_Page;

public class sellerProfileActivity extends AppCompatActivity {

    private ImageView edit_desc;
    private ImageView edit_name;
    private ImageView edit_address;
    private ImageView save_desc;
    private ImageView back_profile;
    private ImageView save_name;
    private ImageView add_items;
    private ImageView save_address;
    private TextView profile_desc;
    private TextView profile_name;
    private TextView profile_address;
    //private MenuItem menu_store;
    //private MenuItem menu_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);

        back_profile = (ImageView)findViewById(R.id.back_profile);
        edit_desc = (ImageView)findViewById(R.id.edit_desc);
        edit_name = (ImageView)findViewById(R.id.edit_name);
        edit_address = (ImageView)findViewById(R.id.edit_address);
        save_desc = (ImageView)findViewById(R.id.save_desc);
        save_name = (ImageView)findViewById(R.id.save_name);
        add_items = (ImageView)findViewById(R.id.add_items);
        save_address = (ImageView)findViewById(R.id.save_address);
        profile_desc = (TextView) findViewById(R.id.profile_desc);
        profile_name = (TextView) findViewById(R.id.profile_name);
        profile_address = (TextView) findViewById(R.id.profile_address);
        //menu_store = (MenuItem)findViewById(R.id.menu_store);
        //menu_add = (MenuItem)findViewById(R.id.menu_add);

        back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sellerProfileActivity.this, HomeActivity.class));
            }
        });

        add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sellerProfileActivity.this, Seller_Page.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.menu_store:
                Toast.makeText(this,"Inventory",Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_add:
                startActivity(new Intent(sellerProfileActivity.this, Seller_Page.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}