package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button remEntry;
    Button newEntry;
    Button confirm;
    EditText entryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton","Vancouver","Montreal","Ottawa","Moscow","Sydney","Berlin","Vienna","Tokyo","Beijing","Osaka","New Delhi","Toronto"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        entryText = findViewById(R.id.text_zone);
        confirm = findViewById(R.id.confirm_button);

        final int[] textType = {0};

        newEntry = findViewById(R.id.new_entry);
        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.new_entry){
                    if (entryText.getVisibility() == View.GONE ){
                        entryText.setVisibility(View.VISIBLE);
                        confirm.setVisibility(View.VISIBLE);
                        textType[0] = 1;
                    }
                }
            }
        });

        remEntry = findViewById(R.id.rem_entry);
        remEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.rem_entry){
                    if (entryText.getVisibility() == View.GONE ){
                        entryText.setVisibility(View.VISIBLE);
                        confirm.setVisibility(View.VISIBLE);
                        textType[0] = 2;
                    }
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = entryText.getText().toString().trim();
                if (!item.isEmpty()){
                    if (textType[0] == 1) {
                        dataList.add(item);
                    } else if (textType[0] == 2){
                        dataList.remove(item);
                    }
                    cityAdapter.notifyDataSetChanged();
                    entryText.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                }
            }
        });

    }
}