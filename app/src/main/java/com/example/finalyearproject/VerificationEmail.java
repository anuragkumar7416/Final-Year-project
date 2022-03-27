package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalyearproject.databinding.ActivityVerificationEmailBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerificationEmail extends AppCompatActivity {

    ActivityVerificationEmailBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificationEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         auth = FirebaseAuth.getInstance();
         user = auth.getCurrentUser();

         binding.btVerify.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(user!=null){
                     user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(getApplicationContext(), "Verification Mail Sent! Please verify", Toast.LENGTH_LONG).show();
                                 startActivity(new Intent(VerificationEmail.this,SignIn.class));
                             }
                             else {
                                 Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                 }
             }
         });

    }
}