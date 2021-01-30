package com.example.aaachat.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaachat.R;
import com.example.aaachat.startup.HomeActivity;
import com.example.aaachat.startup.Profile_Page;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class Phoneno_verify extends AppCompatActivity {
    private EditText phoneno;
    Button verify,option;
    CountryCodePicker ccode;
    FirebaseUser currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneno_verify);
        verify=(Button)findViewById(R.id.Verify);
        option=(Button)findViewById(R.id.optional);

        //check existing user:
        currentuser= FirebaseAuth.getInstance().getCurrentUser();
        if(currentuser!=null)
        {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        ccode=(CountryCodePicker)findViewById(R.id.ccp);
        phoneno=(EditText)findViewById(R.id.phno);
        Intent i=getIntent();
        String phone=i.getStringExtra("Phoneno");
        phoneno.setText(phone);
        String phno=phoneno.getText().toString();
        ccode.registerCarrierNumberEditText(phoneno);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),otp_check.class);
                i.putExtra("phno",ccode.getFullNumberWithPlus().replace(" ",""));
                startActivity(i);
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Profile_Page.class));
            }
        });
    }
}