package com.example.wfd.pantry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wfd.MainActivity;
import com.example.wfd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import DB.Objects.Ingredient;

public class Pantry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        ingredientList = MainActivity.dbHandler.getAvailableIngredients();




        PantryAdapter pantryAdapter = new PantryAdapter(ingredientList, this);

        ListView pantryListView = (ListView) findViewById(R.id.pantryListView);
        pantryListView.setAdapter(pantryAdapter);

        FloatingActionButton addIngredient = (FloatingActionButton) findViewById(R.id.addIngredient);
        addIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(this,Add_Ingredient_Popup.class);
            startActivity(intent);
        });

        FloatingActionButton returnMenu = (FloatingActionButton) findViewById(R.id.pantryReturntoMenuButton);
        returnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

    }


}
