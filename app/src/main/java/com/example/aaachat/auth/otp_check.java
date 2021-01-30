package com.example.aaachat.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aaachat.R;
import com.example.aaachat.buyerMainActivity;
import com.example.aaachat.startup.Profile_Page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class otp_check extends AppCompatActivity {
    private EditText otp;
    private Button verify;
    private ProgressBar progressbar;
    String mVerificationId;
    FirebaseAuth mAuth;
    PhoneAuthProvider.ForceResendingToken token;
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseUser currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_check);
        otp= (EditText) findViewById(R.id.otp);
        mAuth= FirebaseAuth.getInstance();
        verify = (Button) findViewById(R.id.submit);
        progressbar = (ProgressBar) findViewById(R.id.progrss);
        Intent intent = getIntent();
        String Phoneno = intent.getStringExtra("phno");
        sendVerificationCode(Phoneno);
        currentuser= FirebaseAuth.getInstance().getCurrentUser();
        if(currentuser!=null)
        {
            startActivity(new Intent(getApplicationContext(),Profile_Page.class));
        }
        else{
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Phoneno!=null)
                    {
                        verify.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String code=otp.getText().toString();
                                if (code.isEmpty() || code.length() < 6) {
                                    otp.setError("Enter valid code");
                                    otp.requestFocus();
                                    return;
                                }
                                verifyVerificationCode(code);
                            }
                        });
                    }else{
                        Toast.makeText(otp_check.this, "Please enter the phone number", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendVerificationCode(String mobile) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);                     // OnVerificationStateChangedCallbacks
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    //Getting the code sent by SMS
                    String code = phoneAuthCredential.getSmsCode();

                    //sometime the code is not detected automatically
                    //in this case the code will be null
                    //so user has to manually enter the code
                    if (code != null) {
                        otp.setText(code);
                        //verifying the code
                        verifyVerificationCode(code);
                    }
                }


                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(otp_check.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.INVISIBLE);
                }

                //when the code is generated then this method will receive the code.
                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);

                    //storing the verification id that is sent to the user
                    mVerificationId = s;
                    progressbar.setVisibility(View.VISIBLE);
                }
            };

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(otp_check.this,
                        new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //verification successful we will start the profile activity
                                    Intent intent = new Intent(otp_check.this, buyerMainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                } else {

                                    //verification unsuccessful.. display an error message

                                    String message = "Somthing is wrong, we will fix it soon...";

                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                        message = "Invalid code entered...";
                                    }
                                    Toast.makeText(otp_check.this,message,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }
}