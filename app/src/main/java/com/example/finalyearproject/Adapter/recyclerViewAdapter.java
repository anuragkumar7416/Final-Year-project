package com.example.finalyearproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.R;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewholder> {

    ArrayList<Mentors> list = new ArrayList<>();

    public recyclerViewAdapter(ArrayList<Mentors> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public recyclerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_sample,parent,false);
        return new recyclerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewholder holder, int position) {
        //Mentors mentors = list.get(position);
        holder.t1.setText(list.get(position).getName());
        holder.t2.setText(list.get(position).getWorkBackground());
        holder.img.setImageResource(list.get(position).getImage_id());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
