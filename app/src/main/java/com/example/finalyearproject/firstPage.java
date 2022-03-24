package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalyearproject.databinding.ActivityFirstPageBinding;
import com.google.firebase.auth.FirebaseAuth;

public class firstPage extends AppCompatActivity {

    ActivityFirstPageBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();


        binding.btMentee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstPage.this,SignIn.class);
                startActivity(intent);
            }
        });

        binding.btMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstPage.this,Mentor_SignIn.class);
                startActivity(intent);
            }
        });

//        if(auth.getCurrentUser()!=null){
//            Intent intent = new Intent(firstPage.this,MainActivity.class);
//            startActivity(intent);
//        }
    }

}