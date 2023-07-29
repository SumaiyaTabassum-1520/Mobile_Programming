package com.example.eventmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class activity_create_event extends AppCompatActivity {

    private EditText etName,etPlace,etCapacity,etDateAndTime,etBudget,etEmail,etPhone,etDescription;
    private TextView tvError;
    RadioButton rdIndoor, rdOutdoor,rdOnline;
    private void showErrorDialog(String errorMessage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(errorMessage);
        builder.setTitle("Error");
        builder.setCancelable(true);
        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                //dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        etName=findViewById(R.id.etName);
        etPlace=findViewById(R.id.etPlace);
        etCapacity=findViewById(R.id.etCapacity);
        etDateAndTime=findViewById(R.id.etDateAndTime);
        etBudget=findViewById(R.id.etBudget);
        etEmail=findViewById(R.id.etEmail);
        etPhone=findViewById(R.id.etPhone);
        etDescription=findViewById(R.id.etDescription);

        tvError=findViewById(R.id.tvError);

        Button btnSave= findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Save button was clicked");
                String name = etName.getText().toString();
                String place = etPlace.getText().toString();
                String cap = etCapacity.getText().toString();
                String datetime = etDateAndTime.getText().toString();
                String budget = etBudget.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String description = etDescription.getText().toString();
                boolean isIndoorChecked = rdIndoor.isChecked();
                boolean isOutdoorChecked = rdOutdoor.isChecked();
                boolean isOnlineChecked = rdOnline.isChecked();


                String err = "";


                if (!name.matches("[a-zA-Z]+") || name.length() < 4 || name.length() > 12) {
                    err = "Name should have 4-12 letters, ";
                }

                if (place.length() < 6 && name.length() > 64) {
                    err += "Place should be alpha-numeric values with 6-64 characters, ";
                }
                if (cap.length() > 0) {
                    try {
                        int capacity = Integer.parseInt(cap);
                    } catch (Exception e) {
                        err += "Capacity must be greater than zero, ";
                    }
                }
                DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                formatter.setLenient(false);
                try {
                    Date date = formatter.parse(datetime);
                } catch (ParseException e) {
                    err += "Date format invalid ";

                }
                if (budget.length() > 1000) {
                    try {
                        double Budget = Double.parseDouble(budget);
                    } catch (Exception e) {
                        err += "Budget must be decimal value, ";
                    }
                }
                if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                    err += "Invalid email, ";

                }
                if (phone.length() < 8 || phone.length() > 17 || phone.charAt(0) != '+') {
                    err += "Invalid phone number.Must be start with +, ";
                }
                if (description.length() < 10 || description.length() > 1000) {
                    err += "description required more than 10 character not more than 1000 character.";
                }
                if (isIndoorChecked == false || isOutdoorChecked == false || isOnlineChecked == false) {
                    err += "One button should be checked";
                }
                if (err.length() > 0) {
                    showErrorDialog(err);
                }

            }
        });

    }
}