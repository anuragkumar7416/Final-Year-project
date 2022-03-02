package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalyearproject.Models.mentees;
import com.example.finalyearproject.databinding.ActivityCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {

     private FirebaseAuth auth;
     FirebaseDatabase database;
     ActivityCreateAccountBinding  binding;
     ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(CreateAccount.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");

        binding.btCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),binding.etConfirmPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            mentees mentee = new mentees(binding.etusername.getText().toString(),
                                    binding.etPhoneNo.getText().toString(),
                                    binding.etEmail.getText().toString(),
                                    binding.etConfirmPassword.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Mentees").child(id).setValue(mentee);

                            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();


                        } else {

                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

        binding.goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccount.this,SignIn.class);
                startActivity(intent);
            }
        });
    }
}