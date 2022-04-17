package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalyearproject.Models.mentees;
import com.example.finalyearproject.databinding.ActivityCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateAccount extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

     private FirebaseAuth auth;
     FirebaseDatabase database;
     ActivityCreateAccountBinding  binding;
     ProgressDialog progressDialog;
    final Calendar myCalendar= Calendar.getInstance();
    String[] gender ={"Select your gender","Male","Female","Others"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(CreateAccount.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (this,android.R.layout.select_dialog_item,gender);
//        //Getting the instance of AutoCompleteTextView
//        AutoCompleteTextView actv= binding.autoCompleteTextView1;
//        actv.setThreshold(1);//will start working from first character
//        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//        actv.setTextColor(Color.RED);
        Spinner spin = binding.spinGender;
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        binding.btCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),binding.etConfirmPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            mentees mentee = new mentees(binding.etusername.getText().toString(),
                                    binding.etPhoneNo.getText().toString(),
                                    binding.etEmail.getText().toString(),
                                    binding.etConfirmPassword.getText().toString(),
                                    binding.etDOB.getText().toString(),
                                    binding.spinGender.getSelectedItem().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Mentees").child(id).setValue(mentee);

                            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();


                        } else {

                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

        binding.goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccount.this,SignIn.class);
                startActivity(intent);
                finish();
            }
        });

        binding.backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccount.this,SignIn.class);
                startActivity(intent);
                finish();


            }
        });
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        binding.etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CreateAccount.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        binding.etDOB.setText(dateFormat.format(myCalendar.getTime()));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(),gender[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}