package com.example.finalyearproject.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalyearproject.Models.Mentors;
import com.example.finalyearproject.Models.mentees;
import com.example.finalyearproject.R;
import com.example.finalyearproject.databinding.FragmentMentorProfileBinding;
import com.example.finalyearproject.databinding.FragmentProfileBinding;
import com.example.finalyearproject.firstPage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class MentorProfileFragment extends Fragment {



    public MentorProfileFragment() {
        // Required empty public constructor
    }



  FragmentMentorProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMentorProfileBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();


        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), firstPage.class);
                startActivity(intent);
                getActivity().finish();


            }
        });

        database.getReference().child("Mentors").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Mentors mentors = snapshot.getValue(Mentors.class);
//                Picasso.get()
//                        .load(mentee.getProfilePic())
//                        .into(binding.profileImageView);
                        Glide.with(getContext()).load(mentors.getProfilePic()).into(binding.profileImageView);

                        binding.userEmail.setText(mentors.getEmail());
                        binding.userPhoneNo.setText(mentors.getPhoneNo());
                        binding.userName.setText(mentors.getName());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(),"not updated",Toast.LENGTH_LONG).show();
                        Log.w("TAG", "Failed to read value.", error.toException());

                    }
                });

        binding.btAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });




        return binding.getRoot();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!= null){
            Uri file = data.getData();
            binding.profileImageView.setImageURI(file);

            final StorageReference reference = storage.getReference().child("profile_pictures")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(),"Image Uploaded",Toast.LENGTH_LONG).show();
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Mentors")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .child("profilePic").setValue(uri.toString());

                        }
                    });

                }
            });

        }
    }
}