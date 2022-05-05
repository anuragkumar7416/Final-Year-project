package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth auth;
    private static int SPLASH_SCREEN_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(user!=null){
                        String userEmail = user.getEmail();
                        if(userEmail.contains("@mentor.com")){
                            Intent intent = new Intent(SplashScreen.this,Mentor_Home.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                    else {
                    Intent i=new Intent(SplashScreen.this,
                            firstPage.class);
                    startActivity(i);

                    }



                finish();

            }
        }, SPLASH_SCREEN_TIME_OUT);


    }
}