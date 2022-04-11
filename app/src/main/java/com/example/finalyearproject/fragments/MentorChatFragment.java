package com.example.finalyearproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalyearproject.Adapter.MenteeAdapter;
import com.example.finalyearproject.Adapter.recyclerViewAdapter;
import com.example.finalyearproject.Models.mentees;
import com.example.finalyearproject.R;
import com.example.finalyearproject.databinding.FragmentMentorChatBinding;
import com.example.finalyearproject.databinding.FragmentMentorProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MentorChatFragment extends Fragment {


    public MentorChatFragment() {
        // Required empty public constructor
    }

    FragmentMentorChatBinding binding;
    ArrayList<mentees> list  = new ArrayList<>();
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMentorChatBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();
        MenteeAdapter adapter = new MenteeAdapter(list,getContext());
        binding.chatRecView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.chatRecView.setLayoutManager(linearLayoutManager);

        database.getReference().child("Mentees").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    mentees mentee = dataSnapshot.getValue(mentees.class);
                    mentee.getUserId(dataSnapshot.getKey());
                    list.add(mentee);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return binding.getRoot();
    }
}