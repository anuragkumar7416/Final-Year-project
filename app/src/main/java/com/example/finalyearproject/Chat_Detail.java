package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.finalyearproject.Adapter.MessageAdapter;
import com.example.finalyearproject.Models.Message;
import com.example.finalyearproject.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

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

       final String senderId = auth.getUid();
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

        final ArrayList<Message> messagesModel =new ArrayList<>();
        final MessageAdapter messageAdapter = new MessageAdapter(messagesModel,this);
        binding.messageRecView.setAdapter(messageAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.messageRecView.setLayoutManager(layoutManager);

        final String senderRoom = senderId + receiverId;
        final String receiverRoom = senderId + receiverId;

        database.getReference().child("Chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messagesModel.clear();
                        for(DataSnapshot snapshot1: snapshot.getChildren()){
                           Message model = snapshot1.getValue(Message.class);

                           messagesModel.add(model);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typedMessage = binding.etMessage.getText().toString();
                final Message model = new Message(senderId,typedMessage);
                model.setTimestamp(new Date().getTime());
                binding.etMessage.setText("");
                database.getReference().child("Chats")
                        .child(senderRoom)
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        database.getReference().child("Chats")
                                .child(receiverRoom)
                                .push()
                                .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
                    }
                });

            }
        });
    }
}