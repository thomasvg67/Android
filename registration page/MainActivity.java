package com.example.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Toast;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText phoneno;
    private EditText password;
    private RadioGroup radioGroup;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        password = findViewById(R.id.password);
        radioGroup = findViewById(R.id.radioGroup);
        Button button = findViewById(R.id.button);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);

        // Load saved data
        loadSavedData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    // Save data to SharedPreferences
                    saveDataToPreferences();

                    // Navigate to MainActivity2
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;

        // Validate first name
        if (firstname.getText().toString().trim().isEmpty()) {
            firstname.setError("First name is required");
            isValid = false;
        } else {
            firstname.setError(null);
        }

        // Validate last name
        if (lastname.getText().toString().trim().isEmpty()) {
            lastname.setError("Last name is required");
            isValid = false;
        } else {
            lastname.setError(null);
        }

        // Validate email
        String emailAddress = email.getText().toString().trim();
        if (emailAddress.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            email.setError("Invalid email address");
            isValid = false;
        } else {
            email.setError(null);
        }

        // Validate phone number
        String phoneNo = phoneno.getText().toString().trim();
        if (phoneNo.isEmpty() || !Pattern.matches("\\d{10}", phoneNo)) {
            phoneno.setError("Invalid phone number");
            isValid = false;
        } else {
            phoneno.setError(null);
        }

        // Validate password
        String pass = password.getText().toString().trim();
        if (pass.isEmpty() || pass.length() < 6) {
            password.setError("Password must be at least 6 characters");
            isValid = false;
        } else {
            password.setError(null);
        }

        // Check if a gender is selected
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    private void saveDataToPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FIRST_NAME", firstname.getText().toString());
        editor.putString("LAST_NAME", lastname.getText().toString());
        editor.putString("EMAIL", email.getText().toString());
        editor.putString("PHONE_NO", phoneno.getText().toString());
        editor.putString("PASSWORD", password.getText().toString());

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String gender = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "Not selected";
        editor.putString("GENDER", gender);

        editor.apply(); // Save data asynchronously
    }

    private void loadSavedData() {
        String firstName = sharedPreferences.getString("FIRST_NAME", "");
        String lastName = sharedPreferences.getString("LAST_NAME", "");
        String emailAddress = sharedPreferences.getString("EMAIL", "");
        String phoneNo = sharedPreferences.getString("PHONE_NO", "");
        String pass = sharedPreferences.getString("PASSWORD", "");
        String gender = sharedPreferences.getString("GENDER", "Not selected");

        firstname.setText(firstName);
        lastname.setText(lastName);
        email.setText(emailAddress);
        phoneno.setText(phoneNo);
        password.setText(pass);

        // Set gender in radioGroup
        if (gender.equals("Male")) {
            ((RadioButton) findViewById(R.id.radioMale)).setChecked(true);
        } else if (gender.equals("Female")) {
            ((RadioButton) findViewById(R.id.radioFemale)).setChecked(true);
        } else {
            radioGroup.clearCheck(); // "Not selected" or default
        }
    }
}
