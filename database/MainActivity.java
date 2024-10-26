package com.example.databaseconnect;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText Name, Mark, Surname;
    TextView DataV;
    MyDatabase database = new MyDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.ed1);
        Surname = findViewById(R.id.ed2);
        Mark = findViewById(R.id.ed3);
        DataV = findViewById(R.id.textView);
        }

    public void saveData(View view) {
        String UserVal = Name.getText().toString();
        String SurnameVal = Surname.getText().toString();
        try {
            Integer PassVal = Integer.parseInt(Mark.getText().toString());
            Boolean result = database.insertdata(UserVal, PassVal, SurnameVal);
            if (result) {
                Toast.makeText(getApplicationContext(), "Data inserted Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Data insertion Failed", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid number for marks", Toast.LENGTH_SHORT).show();
        }
    }

    public void read(View view) {
        Cursor res = database.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("Id: " + res.getString(0) + "\n");
                stringBuffer.append("Name: " + res.getString(1) + "\n");
                stringBuffer.append("Surname: " + res.getString(3) + "\n");
                stringBuffer.append("Marks: " + res.getString(4) + "\n");
            }
            DataV.setText(stringBuffer.toString());
            Toast.makeText(getApplicationContext(), "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
        } else {
            DataV.setText(""); // Clear previous data
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {
        String UserVal = Name.getText().toString();
        String SurnameVal = Surname.getText().toString();
        try {
            Integer PassVal = Integer.parseInt(Mark.getText().toString());
            Boolean result = database.updateData(UserVal, PassVal, SurnameVal);
            if (result) {
                Toast.makeText(getApplicationContext(), "Data updated Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No Rows Affected", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid number for marks", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {
        String UserVal = Name.getText().toString();
        int rowsAffected = database.deletedata(UserVal);
        if (rowsAffected > 0) {
            Toast.makeText(getApplicationContext(), rowsAffected + " Rows Affected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "No matching records found", Toast.LENGTH_SHORT).show();
        }
    }

}