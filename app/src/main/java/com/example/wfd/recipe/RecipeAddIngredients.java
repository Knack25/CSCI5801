package com.example.wfd.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wfd.MainActivity;
import com.example.wfd.R;

import java.util.ArrayList;

import DB.Objects.Ingredient;
import DB.Objects.Recipe;

public class RecipeAddIngredients extends AppCompatActivity {

    Spinner ingredientSpinner;
    ArrayList<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add_ingredients);

        //Get passed recipe from Intent
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");

        ingredientSpinner = findViewById(R.id.Ingredientspinner);
        EditText ammount = findViewById(R.id.addRecipeIngredientAmountEditText);
        Spinner type = findViewById(R.id.amountTypeSpinner);
        Button addIngredient = findViewById(R.id.addIngredientAddButton);

        //Arrange data for the type spinner
        ArrayAdapter<CharSequence> adaptertype = ArrayAdapter.createFromResource(this,R.array.measurements, android.R.layout.simple_spinner_item);
        Log.v("DEBUG",adaptertype.toString());
        adaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adaptertype);

        //Arrange data for the ingredient spinner
        loadIngredientSpinnerData(recipe.getID());

        addIngredient.setOnClickListener(view -> {
            int _ID = findIngredientID(String.valueOf(ingredientSpinner.getSelectedItem()));
            if(
            MainActivity.dbHandler.addRecipeIngredient(String.valueOf(ingredientSpinner.getSelectedItem()),
                    recipe.getID(),_ID,
                    Integer.valueOf(String.valueOf(ammount.getText())),String.valueOf(type.getSelectedItem())
                    )){
                Log.v("DEBUG","Successfully Entered Ingredient: " + _ID +
                        " into recipe: " + recipe.getID());

                Intent intent = new Intent(this, RecipeDetails.class);
                intent.putExtra("Recipe",recipe);
                startActivity(intent);
            }
        });
    }

    private int findIngredientID(String name){
        int ID = 0;

        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName() == name){
                ID = ingredients.get(i).getID();
            }
        }

        return ID;
    }

    private void loadIngredientSpinnerData(int id) {
        ingredients = new ArrayList<>();
        ArrayList<String>   ingredientNames = new ArrayList<>();

        ingredients = MainActivity.dbHandler.getAvailableIngredients();
        for (int i = 0; i < ingredients.size();i++){
            ingredientNames.add(ingredients.get(i).getName());
        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,ingredientNames);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ingredientSpinner.setAdapter(dataAdapter);

    }


}