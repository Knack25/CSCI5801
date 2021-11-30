package com.example.wfd.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wfd.MainActivity;
import com.example.wfd.R;

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

    }
}