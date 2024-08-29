package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText username1;
    private EditText password1;
    private Button buttonClear;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username1 = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        buttonClear = findViewById(R.id.clear);
        loginButton = findViewById(R.id.login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear(v);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    public void login(View view) {

        String username=username1.getText().toString();
        String password=password1.getText().toString();

        String un="admin";
        String pw="admin";

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
        }else if(username.equals(un) && password.equals(pw)){
            Toast.makeText(this,"Login successfull",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,MainActivity2.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    public void clear(View view) {
        username1.setText("");
        password1.setText("");
    }
}