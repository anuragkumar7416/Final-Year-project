package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalyearproject.databinding.ActivityCreateAccountBinding;
import com.example.finalyearproject.databinding.ActivityForgotPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.etEmail.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(ForgotPassword.this,"Please provide your email",Toast.LENGTH_LONG).show();
                }
                else {
                    forgotPassword();
                }
            }
        });


    }

    private void forgotPassword() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Check your email",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgotPassword.this,SignIn.class));
                    finish();

                }else {
                    Toast.makeText(ForgotPassword.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}