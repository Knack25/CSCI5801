package com.example.wfd.pantry;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wfd.R;

import java.util.ArrayList;

import DB.Objects.Ingredient;

public class Pantry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        ArrayList<Ingredient> ingredientList = new ArrayList<>();



        PantryAdapter pantryAdapter = new PantryAdapter(ingredientList, this);

        ListView pantryListView = (ListView) findViewById(R.id.pantryListView);
        pantryListView.setAdapter(pantryAdapter);

    }

}
