package com.example.otpapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText enternumber;

    FirebaseAuth mauth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth= FirebaseAuth.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            Intent i = new Intent(MainActivity.this,Dashboard.class);
            startActivity(i);
            finishAffinity();
        }


         enternumber = findViewById(R.id.inputmobilenumber);
     final Button  getotpbutton = findViewById(R.id.buttongetotp);

        ProgressBar progressBar = findViewById(R.id.progressbar_sending_otp);

         getotpbutton.setOnClickListener(view -> {

             if(!enternumber.getText().toString().trim().isEmpty()){
                 if((enternumber.getText().toString().trim()).length()==10){

                     progressBar.setVisibility(View.VISIBLE);
                     getotpbutton.setVisibility(View.INVISIBLE);

                     PhoneAuthProvider.getInstance().verifyPhoneNumber(
                             "+91" + enternumber.getText().toString(),
                             60,
                             TimeUnit.SECONDS,
                             MainActivity.this,
                             new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                 @Override
                                 public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                     progressBar.setVisibility(View.GONE);
                                     getotpbutton.setVisibility(View.VISIBLE);
                                 }

                                 @Override
                                 public void onVerificationFailed(@NonNull FirebaseException e) {
                                     progressBar.setVisibility(View.GONE);
                                     getotpbutton.setVisibility(View.VISIBLE);
                                     Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                 }

                                 @Override
                                 public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                     progressBar.setVisibility(View.GONE);
                                     getotpbutton.setVisibility(View.VISIBLE);
                                     Intent intent = new Intent(getApplicationContext(),Enterotp.class);
                                     intent.putExtra("mobile",enternumber.getText().toString());
                                     intent.putExtra("backendotp",backendotp);
                                     startActivity(intent);
                                 }
                             }
                     );

                 }else {
                     Toast.makeText(MainActivity.this,"Please Enter correct number",Toast.LENGTH_SHORT).show();
                 }
             }else {
                 Toast.makeText(MainActivity.this,"Please Enter mobile number",Toast.LENGTH_SHORT).show();

             }

         });
    }
}