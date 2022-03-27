package com.example.finalyearproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.Chat_Detail;
import com.example.finalyearproject.MentorProfile;
import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.R;
import com.example.finalyearproject.fragments.CategoriesFragment;
import com.example.finalyearproject.fragments.HomeFragment;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewholder> {

    ArrayList<Mentors> list = new ArrayList<>();
    Context context;

    public recyclerViewAdapter(ArrayList<Mentors> list, Context context) {
        this.list = list;
        this.context = context;
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

        Mentors mentors = list.get(position);

        holder.t1.setText(list.get(position).getName());
        holder.t2.setText(list.get(position).getWorkBackground());
        holder.img.setImageResource(list.get(position).getImage_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MentorProfile.class);
                intent.putExtra("userId",mentors.getName());
                intent.putExtra("userEmail",mentors.getEmail());
                intent.putExtra("userName",mentors.getName());
                intent.putExtra("workBackground",mentors.getWorkBackground());
                intent.putExtra("userPic",mentors.getImage_id());
                context.startActivity(intent);
            }
        });

    }





    @Override
    public int getItemCount() {
        return list.size();
    }
}
