package com.example.finalyearproject.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.R;

public class chatViewHolder extends RecyclerView.ViewHolder {

    ImageView img1;
    TextView t3, t4;
    public chatViewHolder(@NonNull View itemView) {
        super(itemView);
        img1 = itemView.findViewById(R.id.profileImage);
        t3 = itemView.findViewById(R.id.sampleChatName);
        t4 = itemView.findViewById(R.id.profession);
    }
}
