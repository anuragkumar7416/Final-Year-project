package com.example.finalyearproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalyearproject.Chat_Detail;
import com.example.finalyearproject.Models.mentees;
import com.example.finalyearproject.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenteeAdapter extends RecyclerView.Adapter<MenteeAdapter.MessageViewHolder>{
    ArrayList<mentees> list = new ArrayList<>();
    Context context;

    public MenteeAdapter(ArrayList<mentees> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sample,parent,false);
        return new MessageViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        mentees mentee = list.get(position);

        holder.t5.setText(list.get(position).getName());
        holder.t6.setText(list.get(position).getWorkBackground());
        Glide.with(context).load(mentee.getProfilePic())
                .placeholder(R.drawable.user)
                .into(holder.img2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Chat_Detail.class);
                intent.putExtra("userId",mentee.getUserId());
                intent.putExtra("userEmail",mentee.getEmail());
                intent.putExtra("userName",mentee.getName());
                intent.putExtra("workBackground",mentee.getWorkBackground());
                intent.putExtra("userPic",mentee.getProfilePic());
                context.startActivity(intent);
            }
        });

    }





    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MessageViewHolder extends RecyclerView.ViewHolder{

        TextView t5,t6;
        CircleImageView img2;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            img2 =(CircleImageView) itemView.findViewById(R.id.profileImage);
            t5 =(TextView) itemView.findViewById(R.id.sampleChatName);
            t6 =(TextView) itemView.findViewById(R.id.profession);
        }
    }
}
