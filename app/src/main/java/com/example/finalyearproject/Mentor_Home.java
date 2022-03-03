package com.example.finalyearproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Mentor_Home extends AppCompatActivity {

    EditText secretCodeBox;
    Button joinBtn, shareBtn;
//    private jit JitsiMeet;
//    private BuildersKt JitsiMeetActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_home);

        secretCodeBox = findViewById(R.id.codeBox);
        joinBtn = findViewById(R.id.joinBtn);
        shareBtn = findViewById(R.id.shareBtn);

        joinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(secretCodeBox.getText().toString().isEmpty()){
                    Toast.makeText(Mentor_Home.this,"Please Enter The Code",Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                .setServerURL(new URL("https:/meet.jit.si"))
                                .setRoom(secretCodeBox.getText().toString())
                                .setAudioOnly(true)
                                .setWelcomePageEnabled(false)
                                .build();

                        JitsiMeetActivity.launch(Mentor_Home.this,options);

                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}