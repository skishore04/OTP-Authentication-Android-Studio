package com.example.otpapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Enterotp extends AppCompatActivity {

    EditText inputnumber1,inputnumber2,inputnumber3,inputnumber4,inputnumber5,inputnumber6;

    String getotpbackend;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterotp);

        inputnumber1 = findViewById(R.id.inputotp1);
        inputnumber2 = findViewById(R.id.inputotp2);
        inputnumber3 = findViewById(R.id.inputotp3);
        inputnumber4 = findViewById(R.id.inputotp4);
        inputnumber5 = findViewById(R.id.inputotp5);
        inputnumber6 = findViewById(R.id.inputotp6);

        TextView textView = findViewById(R.id.textmobileshownumber);
        textView.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));
        getotpbackend = getIntent().getStringExtra("backendotp");

       final ProgressBar progressBarverigyotp = findViewById(R.id.progressbar_verify_otp);


       final Button verifybuttonclick= findViewById(R.id.buttonverifyotp);
        verifybuttonclick.setOnClickListener(view -> {
            if(!inputnumber1.getText().toString().trim().isEmpty() && !inputnumber2.getText().toString().trim().isEmpty() && !inputnumber3.getText().toString().trim().isEmpty() && !inputnumber4.getText().toString().trim().isEmpty() && !inputnumber5.getText().toString().trim().isEmpty() && !inputnumber6.getText().toString().trim().isEmpty()){
                String entercodeotp = inputnumber1.getText().toString()+
                        inputnumber2.getText().toString() +
                        inputnumber3.getText().toString() +
                        inputnumber4.getText().toString() +
                        inputnumber5.getText().toString() +
                        inputnumber6.getText().toString();

                if(getotpbackend!=null){

                    progressBarverigyotp.setVisibility(View.VISIBLE);
                    verifybuttonclick.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            getotpbackend,entercodeotp
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(task -> {
                                progressBarverigyotp.setVisibility(View.GONE);
                                verifybuttonclick.setVisibility(View.VISIBLE);

                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(Enterotp.this,"Enter the correct OTP",Toast.LENGTH_SHORT).show();
                                }
                            });

                }
                else {
                    Toast.makeText(Enterotp.this,"Please check your Internet Connection",Toast.LENGTH_SHORT).show();
                }





//                    Toast.makeText(Enterotp.this,"OTP Verify",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Enterotp.this,"Please Enter all numbers",Toast.LENGTH_SHORT).show();
            }
        });


        numberotpmove();

        findViewById(R.id.textresendotp).setOnClickListener(view -> PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + getIntent().getStringExtra("mobile"),
                60,
                TimeUnit.SECONDS,
                Enterotp.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                        Toast.makeText(Enterotp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                        getotpbackend = newbackendotp;
                        Toast.makeText(Enterotp.this,"OTP sent Successfully",Toast.LENGTH_SHORT).show();

                    }
                }
        ));
    }

    private void numberotpmove() {

        inputnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    inputnumber6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}