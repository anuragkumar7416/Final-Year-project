package com.example.finalyearproject.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.R;

public class recyclerViewholder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView t1, t2;
    public recyclerViewholder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.sampleImageView);
        t1 = itemView.findViewById(R.id.sampleTextView1);
        t2 = itemView.findViewById(R.id.sampleTextView2);

    }
}
