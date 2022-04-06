package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.finalyearproject.databinding.ActivityChatDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Chat_Detail extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    int imagevalue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

       String senderId = auth.getUid();
       String receiverId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
       // Bundle bundle = getIntent().getExtras();

        // if bundle is not null then get the image value
//        if (bundle != null) {
//            int image_value = bundle.getInt("userPic");
//            binding.profilePic.setImageResource(image_value);
//        }
       // binding.profilePic.setImageResource(imagevalue);
       String profilePic = getIntent().getStringExtra("userPic");

       binding.userName.setText(userName);
        //Picasso.get().load(profilePic).placeholder(R.drawable.user).into(binding.profilePic);
        Glide.with(this).load(profilePic).into(binding.profilePic);
    }
}