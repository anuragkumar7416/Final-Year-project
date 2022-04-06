package com.example.finalyearproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalyearproject.Chat_Detail;
import com.example.finalyearproject.MentorProfile;
import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class chatAdapter extends FirebaseRecyclerAdapter<Mentors,chatViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public chatAdapter(@NonNull FirebaseRecyclerOptions<Mentors> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull chatViewHolder holder, int position, @NonNull Mentors model) {
        holder.t3.setText(model.getName());
        holder.t4.setText(model.getWorkBackground());
        Glide.with(holder.img1.getContext()).load(model.getProfilePic()).into(holder.img1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Chat_Detail.class);
                intent.putExtra("userId",model.getUserId());
                //intent.putExtra("userEmail",mentors.getEmail());
                intent.putExtra("userName",model.getName());
                intent.putExtra("workBackground",model.getWorkBackground());
                intent.putExtra("userPic",model.getProfilePic());
                v.getContext().startActivity(intent);
            }
        });



    }

    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample,parent,false);
        return new chatViewHolder(view);
    }

//    ArrayList<Mentors> list = new ArrayList<>();
//    Context context;

//    public chatAdapter(ArrayList<Mentors> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }
//    @NonNull
//    @Override
//    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.sample,parent,false);
//        return new chatViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {
//        Mentors mentors = list.get(position);
//        holder.t3.setText(list.get(position).getName());
//        holder.t4.setText(list.get(position).getWorkBackground());
//        holder.img1.setImageResource(list.get(position).getImage_id());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, Chat_Detail.class);
//                intent.putExtra("userId",mentors.getUserId());
//                intent.putExtra("userEmail",mentors.getEmail());
//                intent.putExtra("userName",mentors.getName());
//                intent.putExtra("workBackground",mentors.getWorkBackground());
//                intent.putExtra("userPic",mentors.getImage_id());
//                context.startActivity(intent);
//            }
//        });
//
//
//    }

//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
}
