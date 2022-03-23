package com.example.finalyearproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalyearproject.Adapter.recyclerViewAdapter;
import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.Models.mentees;
import com.example.finalyearproject.R;
import com.example.finalyearproject.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    FragmentHomeBinding binding;
    ArrayList<Mentors> list = new ArrayList<>();
    FirebaseAuth auth;
    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference().child("Mentees").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mentees mentee = snapshot.getValue(mentees.class);
                        if (mentee.getName()!=null) {
                            binding.displayUserName.setText(mentee.getName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        recyclerViewAdapter adapter = new recyclerViewAdapter(list);
        binding.recHome.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.recHome.setLayoutManager(linearLayoutManager);

        list.add(new Mentors("Ashneer Grover",R.drawable.ashneer_grover,"Founder of Bharatpe"));
        list.add(new Mentors("Vineeta Singh",R.drawable.vineeta_singh,"Founder & CEO of Sugar Cosmetics"));
        list.add(new Mentors("Ratan tata",R.drawable.ratan_tata,"Business Man & Co-founder of Tata Motors"));
        list.add(new Mentors("Peyush Bansal",R.drawable.peyush_bansal,"Founder of Lenskart"));
        list.add(new Mentors("Falguni Nayar",R.drawable.falguni_nayar,"Founder of Nyakaa"));
        list.add(new Mentors("Jhonny",R.drawable.profile,"UI/UX Designer works at Unsplash"));





        return binding.getRoot();
    }
}