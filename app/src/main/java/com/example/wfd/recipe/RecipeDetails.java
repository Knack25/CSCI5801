package com.example.wfd.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wfd.MainActivity;
import com.example.wfd.R;
import com.example.wfd.pantry.PantryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import DB.Objects.Ingredient;
import DB.Objects.Recipe;

public class RecipeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);


        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");


        Log.v("DEBUG","Received Recipe with name of: " + recipe.getName() +
                " and an ID of: " + recipe.getID());

        ingredientList = MainActivity.dbHandler.getRecipeIngredients(recipe.getID());

        Log.v("DEBUG", "Ingredients List has a size of: " + ingredientList.size());

        TextView name = (TextView) findViewById(R.id.recipeDetailName);
        EditText description = (EditText) findViewById(R.id.recipeDetailDescription);
        EditText steps = (EditText) findViewById(R.id.recipeDetailSteps);
        FloatingActionButton addIngredient = (FloatingActionButton) findViewById(R.id.recipeDetailAddIngredient);


        name.setText(recipe.getName());
        description.setText(recipe.getDescription());
        steps.setText(recipe.getSteps());

        RecipeDetailAdapter recipeDetailAdapter = new RecipeDetailAdapter(ingredientList, this);

        ListView recipeDetailListView = (ListView) findViewById(R.id.recipeDetail_Ingredient_List_View);
        recipeDetailListView.setAdapter(recipeDetailAdapter);




        addIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecipeAddIngredients.class);
            intent.putExtra("Recipe",recipe);
            startActivity(intent);
        });



    }
}