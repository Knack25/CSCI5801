package com.example.wfd.pantry;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wfd.R;

import java.util.ArrayList;

public class Pantry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        ArrayList<String> dbList = new ArrayList<>();

        dbList.add("Entry one");
        dbList.add("Entry two");

        PantryAdapter pantryAdapter = new PantryAdapter(dbList, this);

        ListView pantryListView = (ListView) findViewById(R.id.pantryListView);
        pantryListView.setAdapter(pantryAdapter);

    }

}
