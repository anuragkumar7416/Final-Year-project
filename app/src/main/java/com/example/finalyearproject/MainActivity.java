package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.finalyearproject.fragments.AccountFragment;
import com.example.finalyearproject.fragments.CategoriesFragment;
import com.example.finalyearproject.fragments.HomeFragment;
import com.example.finalyearproject.fragments.Mentorsfragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction().replace(R.id.layoutNav,new HomeFragment()).commit();

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId()){
                    case R.id.home:
                        temp = new HomeFragment();
                        break;
                    case R.id.myMentors:
                        temp = new Mentorsfragment();
                        break;
                    case R.id.categories:
                        temp = new CategoriesFragment();
                        break;
                    case R.id.profile:
                        temp = new AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutNav,temp).commit();
                return true;
            }
        });
    }
}