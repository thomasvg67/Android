package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView tv;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        String msg = getIntent().getStringExtra("mykey");
        if (msg != null) {
            tv.setText("Welcome " + msg);
        } else {
            tv.setText("Welcome Guest"); // Fallback if msg is null
        }

        lv = findViewById(R.id.listview);
        String[] courses = {"MCA", "MBA", "BBA", "BCA"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        lv.setAdapter(adapter);

        // Attach listener
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String itemName = lv.getItemAtPosition(i).toString();
        Toast.makeText(this, itemName, Toast.LENGTH_SHORT).show();
    }
}
