package com.example.finalyearproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalyearproject.Adapter.recyclerViewAdapter;
import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.R;
import com.example.finalyearproject.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    FragmentHomeBinding binding;
    ArrayList<Mentors> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        recyclerViewAdapter adapter = new recyclerViewAdapter(list);
        binding.recHome.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.recHome.setLayoutManager(linearLayoutManager);

        list.add(new Mentors("Jhonny",R.drawable.pic,"UI/UX Designer works at Unsplash"));
        list.add(new Mentors("Jhonny",R.drawable.pic,"UI/UX Designer works at Unsplash"));
        list.add(new Mentors("Jhonny",R.drawable.profile,"UI/UX Designer works at Unsplash"));
        list.add(new Mentors("Jhonny",R.drawable.profile,"UI/UX Designer works at Unsplash"));
        list.add(new Mentors("Jhonny",R.drawable.profile,"UI/UX Designer works at Unsplash"));
        list.add(new Mentors("Jhonny",R.drawable.profile,"UI/UX Designer works at Unsplash"));





        return binding.getRoot();
    }
}