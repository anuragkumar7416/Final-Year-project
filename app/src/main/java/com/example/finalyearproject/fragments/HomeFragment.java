package com.example.finalyearproject.fragments;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalyearproject.Adapter.NewsAdapter;
import com.example.finalyearproject.Adapter.recyclerViewAdapter;
import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.Models.News;
import com.example.finalyearproject.Models.mentees;
import com.example.finalyearproject.MySingleton;
import com.example.finalyearproject.R;
import com.example.finalyearproject.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    FragmentHomeBinding binding;
    ArrayList<Mentors> list = new ArrayList<>();
    FirebaseAuth auth;
    FirebaseDatabase database;
    NewsAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        binding.recNews.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchdata();
        mAdapter = new NewsAdapter(this::onItemClicked);
        binding.recNews.setAdapter(mAdapter);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference().child("Mentees").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mentees mentee = snapshot.getValue(mentees.class);
                            binding.displayUserName.setText(mentee.getName());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        recyclerViewAdapter adapter = new recyclerViewAdapter(list,getContext());
        binding.recHome.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.recHome.setLayoutManager(linearLayoutManager);
        list.add(new Mentors("Ashneer Grover",R.drawable.ashneer_grover,"Ashneer Grover was born in Delhi, India on 14 June 1982. He has completed his graduation with Indian Institute of Technology Delhi and Indian Institute of Management Ahmedabad. Before starting the Bharat Pe he has worked in various companies like Kotak Investment Bank, Grofers, PC Jeweller Ltd and American Express. Bharat Pe, the payment application was launched by Ashneer Grover in 2018, since then the application is in use. At present the application has 1 Crore+ download on Play Store.","ashneer@mentor.com"));
        list.add(new Mentors("Vineeta Singh",R.drawable.vineeta_singh,"Vineeta Singh is an entrepreneur from India. At Sugar Cosmetics Corp., she is the CEO and co-founder. Additionally, she judged the reality television series Shark Tank India produced by Sony Entertainment Television. Its beauty brand is available in more than 130 cities with more than 35,000 points of sale.","vineetasingh@mentor.com"));
        list.add(new Mentors("Ratan tata",R.drawable.ratan_tata,"Business Man & Co-founder of Tata Motors Ratan Naval Tata is an Indian industrialist, philanthropist, and a former chairman of Tata Sons. He was also chairman of Tata Group, from 1990 to 2012, and again, as interim chairman, from October 2016 through February 2017, and continues to head its charitable trusts","ratantata@mentor.com"));
        list.add(new Mentors("Peyush Bansal",R.drawable.peyush_bansal,"Peyush Bansal is the founder and CEO of Lenskart. He is also a judge of the famous Indian Shark Tank.Peyush Bansal???s training for the Honors Bachelor of Engineering course was completed at McGill University, in the Electrical ??? IT, Control and Automation branch from 2002 to 2006.Peyush Bansal obtained her postgraduate degree in entrepreneurship from IIM Bangalore, which made Peyush Bansal wiki strong enough to start a new business and change his life.","peyush@mentor.com" ));
        list.add(new Mentors("Falguni Nayar",R.drawable.falguni_nayar,"Falguni Nayar is an Indian businesswoman and billionaire who is the founder and CEO of the beauty and lifestyle retail company Nykaa. Nayar is one of two self-made female, Indian billionaires.","falguninayar@mentor.com"));
        //list.add(new Mentors("Jhonny",R.drawable.profile,"UI/UX Designer works at Unsplash"));



        return binding.getRoot();
    }

    private void fetchdata() {

        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=3ba2909f6b3247b1b4b21d89a9f7e5b0";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");
                    ArrayList<News> newsArrayList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject newsJsonObject = jsonArray.getJSONObject(i);
                        News news = new News(newsJsonObject.getString("title"), newsJsonObject.getString("author"), newsJsonObject.getString("url"), newsJsonObject.getString("urlToImage"));

                        newsArrayList.add(news);
                    }
                    mAdapter.updateNews(newsArrayList);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        )
        {

            @Override
            public Map<String, String> getHeaders () throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }


    public void onItemClicked(News item) {


        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabColorSchemeParams defaultColors = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(Color.parseColor("#E53935"))
                .build();
        builder.setDefaultColorSchemeParams(defaultColors);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.setPackage("com.android.chrome");
        customTabsIntent.launchUrl(getContext(), Uri.parse(item.getUrl()));


    }
}