package com.example.registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvEmail;
    private TextView tvPhoneNo;
    private TextView tvPassword;
    private TextView tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize views
        tvFirstName = findViewById(R.id.firstNameTextView);
        tvLastName = findViewById(R.id.lastNameTextView);
        tvEmail = findViewById(R.id.emailTextView);
        tvPhoneNo = findViewById(R.id.phoneNoTextView);
        tvPassword = findViewById(R.id.passwordTextView);
        tvGender = findViewById(R.id.genderTextView);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);

        String firstName = sharedPreferences.getString("FIRST_NAME", "N/A");
        String lastName = sharedPreferences.getString("LAST_NAME", "N/A");
        String email = sharedPreferences.getString("EMAIL", "N/A");
        String phoneNo = sharedPreferences.getString("PHONE_NO", "N/A");
        String password = sharedPreferences.getString("PASSWORD", "N/A");
        String gender = sharedPreferences.getString("GENDER", "Not selected");

        // Display data
        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvEmail.setText(email);
        tvPhoneNo.setText(phoneNo);
        tvPassword.setText(password);
        tvGender.setText(gender);
    }
}
