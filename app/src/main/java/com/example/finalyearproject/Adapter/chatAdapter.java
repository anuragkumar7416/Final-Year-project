package com.example.finalyearproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.Chat_Detail;
import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.R;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter<chatViewHolder> {

    ArrayList<Mentors> list = new ArrayList<>();
    Context context;

    public chatAdapter(ArrayList<Mentors> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sample,parent,false);
        return new chatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {
        Mentors mentors = list.get(position);
        holder.t3.setText(list.get(position).getName());
        holder.t4.setText(list.get(position).getWorkBackground());
        holder.img1.setImageResource(list.get(position).getImage_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chat_Detail.class);
                intent.putExtra("userId",mentors.getUserId());
                intent.putExtra("userName",mentors.getName());
                intent.putExtra("userPic",mentors.getProfilePic());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
