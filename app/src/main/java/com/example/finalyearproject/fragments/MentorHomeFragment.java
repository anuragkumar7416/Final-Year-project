package com.example.finalyearproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyearproject.R;
import com.example.finalyearproject.databinding.FragmentCategoriesBinding;
import com.example.finalyearproject.databinding.FragmentMentorHomeBinding;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MentorHomeFragment extends Fragment {




    public MentorHomeFragment() {
        // Required empty public constructor
    }

    FragmentMentorHomeBinding binding;
    EditText secretCodeBox;
    Button joinBtn, shareBtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMentorHomeBinding.inflate(inflater,container,false);
        secretCodeBox = binding.codeBox;
        joinBtn = binding.joinBtn;
        shareBtn = binding.shareBtn;


        try{
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https:/meet.jit.si"))

                    .setAudioOnly(true)
                    .setWelcomePageEnabled(false)
                    .build();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        joinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (secretCodeBox.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter The Code", Toast.LENGTH_SHORT).show();
                } else {
                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                            .setRoom(secretCodeBox.getText().toString())
                            .setWelcomePageEnabled(false)
                            .build();
                    JitsiMeetActivity.launch(getActivity(),options);
                }
            }
        });

        return binding.getRoot();
    }
}