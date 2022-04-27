package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalyearproject.databinding.ActivityWorkBackgroundBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class WorkBackground extends AppCompatActivity {

    ActivityWorkBackgroundBinding binding;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkBackgroundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.workBackgroundBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("Mentees")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("workBackground").setValue(binding.etWorkBackground.getEditText().getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            onBackPressed();
                        }else{
                            Toast.makeText(WorkBackground.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                ;

            }
        });
    }
}