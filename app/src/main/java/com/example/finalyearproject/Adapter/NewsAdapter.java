package com.example.finalyearproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalyearproject.Models.News;
import com.example.finalyearproject.R;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.newsViewHolder> {

    private  static ArrayList<News> items = new ArrayList<>();

    private static NewsItemClicked listener;

    public NewsAdapter(NewsItemClicked listener) {

        this.listener = listener;
    }

    @NonNull

    @Override
    public newsViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  =inflater.inflate(R.layout.item_news,parent,false);
        return new newsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  newsViewHolder holder, int position) {
//        News news = items.get(position);
       holder.textView.setText(items.get(position).getTitle());
       holder.authorName.setText(items.get(position).getAuthor());
       Glide.with(holder.itemView.getContext()).load(items.get(position).getImageUrl()).into(holder.imageView);



    }
    public void updateNews(ArrayList<News> updatedNews){
        items.clear();
        items.addAll(updatedNews);

        notifyDataSetChanged();

    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class newsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         TextView textView;
         TextView authorName;
         ImageView imageView;
        NewsItemClicked itemClicked;

        public newsViewHolder(@NonNull  View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.title_news);
            authorName = itemView.findViewById(R.id.author_name);
            imageView = itemView.findViewById(R.id.newsImage);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            listener.onItemClicked(items.get(position));
        }
    }

    public interface NewsItemClicked{
        void onItemClicked(News item);
    }


}
