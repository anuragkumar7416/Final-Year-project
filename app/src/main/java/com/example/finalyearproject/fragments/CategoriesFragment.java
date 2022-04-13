package com.example.finalyearproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalyearproject.Adapter.MenteeAdapter;
import com.example.finalyearproject.Adapter.chatAdapter;
import com.example.finalyearproject.Adapter.recyclerViewAdapter;
import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.Models.mentees;
import com.example.finalyearproject.R;
import com.example.finalyearproject.databinding.FragmentCategoriesBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment {



    public CategoriesFragment() {
        // Required empty public constructor
    }
    FragmentCategoriesBinding binding;
    ArrayList<Mentors> list = new ArrayList<>();
    FirebaseAuth auth;
    FirebaseDatabase database;
    chatAdapter adapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCategoriesBinding.inflate(inflater,container,false);

        // Inflate the layout for this fragment
//        chatAdapter adapter = new chatAdapter(list,getContext());
//        binding.chatRecView.setAdapter(adapter);
//
//
//        binding.chatRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//
//        binding.chatRecView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        FirebaseRecyclerOptions<Mentors> options =
//                new FirebaseRecyclerOptions.Builder<Mentors>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Mentors"), Mentors.class)
//                        .build();
//
//        adapter = new chatAdapter(options);
//        binding.chatRecView.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        chatAdapter adapter = new chatAdapter(list,getContext());
        binding.chatRecView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.chatRecView.setLayoutManager(linearLayoutManager);

        database.getReference().child("Mentors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Mentors mentors = dataSnapshot.getValue(Mentors.class);
                    mentors.setUserId(dataSnapshot.getKey());
                    list.add(mentors);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//

//        list.add(new Mentors("Ashneer Grover",R.drawable.ashneer_grover,"Founder of Bharatpe"));
//        list.add(new Mentors("Vineeta Singh",R.drawable.vineeta_singh,"Founder & CEO of Sugar Cosmetics"));
//        list.add(new Mentors("Ratan tata",R.drawable.ratan_tata,"Business Man & Co-founder of Tata Motors"));
//        list.add(new Mentors("Peyush Bansal",R.drawable.peyush_bansal,"Founder of Lenskart"));
//        list.add(new Mentors("Falguni Nayar",R.drawable.falguni_nayar,"Founder of Nyakaa"));
//        list.add(new Mentors("Jhonny",R.drawable.profile,"UI/UX Designer works at Unsplash"));
//
//
//



        return binding.getRoot();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
}