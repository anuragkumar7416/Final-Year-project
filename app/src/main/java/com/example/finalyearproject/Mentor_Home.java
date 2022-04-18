package com.example.finalyearproject;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.finalyearproject.databinding.ActivityMentorHomeBinding;
import com.example.finalyearproject.fragments.AccountFragment;
import com.example.finalyearproject.fragments.CategoriesFragment;
import com.example.finalyearproject.fragments.HomeFragment;
import com.example.finalyearproject.fragments.MentorChatFragment;
import com.example.finalyearproject.fragments.MentorHomeFragment;
import com.example.finalyearproject.fragments.MentorProfileFragment;
import com.example.finalyearproject.fragments.Mentorsfragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Mentor_Home extends AppCompatActivity {

    EditText secretCodeBox;
    Button joinBtn, shareBtn;
    BottomNavigationView bottomNavigationView;
    ActivityMentorHomeBinding binding;
//    private jit JitsiMeet;
//    private BuildersKt JitsiMeetActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_home);
        getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction().replace(R.id.mentorlayoutNav,new MentorHomeFragment()).commit();
        bottomNavigationView = findViewById(R.id.mentorbottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId()){
                    case R.id.mentor_home:
                        temp = new MentorHomeFragment();
                        break;
                    case R.id.mentor_chats:
                        temp = new MentorChatFragment();
                        break;
                    case R.id.mentor_profile:
                        temp = new MentorProfileFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.mentorlayoutNav,temp).commit();
                return true;
            }
        });





    }
}