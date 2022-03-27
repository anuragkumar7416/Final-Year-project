package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalyearproject.databinding.ActivityMentorProfileBinding;

public class MentorProfile extends AppCompatActivity {

    ActivityMentorProfileBinding binding;
    int res_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMentorProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();


        String userName = getIntent().getStringExtra("userName");
        String userEmail = getIntent().getStringExtra("userEmail");
        String workBackground = getIntent().getStringExtra("workBackground");
        Bundle bundle =  getIntent().getExtras();

        if(bundle!=null){
             res_image = bundle.getInt("userPic");
            binding.profileImageView.setImageResource(res_image);
        }

        binding.userName.setText(userName);
        binding.userEmail.setText(userEmail);
        binding.aboutMentor.setText(workBackground);
        binding.chatBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MentorProfile.this, Chat_Detail.class);
               // intent.putExtra("userId",mentors.getUserId());
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("userName",userName);
                //intent.putExtra("workBackground",mentors.getWorkBackground());
                intent.putExtra("userPic",res_image);
                startActivity(intent);
            }
        });
    }
}