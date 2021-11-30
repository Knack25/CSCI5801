package com.example.wfd.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.wfd.MainActivity;
import com.example.wfd.R;
import com.example.wfd.pantry.Add_Ingredient_Popup;
import com.example.wfd.pantry.PantryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import DB.Objects.Ingredient;
import DB.Objects.Recipe;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);



        ArrayList<Recipe> recipeList = new ArrayList<>();

        recipeList = MainActivity.dbHandler.getRecipeList();


        RecipeAdapter recipeAdapter = new RecipeAdapter(recipeList, this);

        ListView recipeListView = (ListView) findViewById(R.id.recipeListView);
        recipeListView.setAdapter(recipeAdapter);

        FloatingActionButton addRecipe = (FloatingActionButton) findViewById(R.id.addRecipe);
        addRecipe.setOnClickListener(v -> {
            Intent intent = new Intent(this, Add_Recipe_Popup.class);
            startActivity(intent);
        });

    }
}