package com.example.wfd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wfd.databinding.ActivityMainBinding;
import com.example.wfd.pantry.Pantry;
import com.example.wfd.recipe.RecipeActivity;
import com.example.wfd.ui.main.SectionsPagerAdapter;

import DB.DBHandler;
import DB.Objects.Recipe;
import io.realm.mongodb.App;


public class MainActivity extends AppCompatActivity {

   // public static AtomicReference<User> user = new AtomicReference<User>();
    public static App app;
    //private static final AtomicBoolean success = new AtomicBoolean(false);
    //private static Ingredient testingredient;

    private static final String TAG = "WFD";

    public static DBHandler dbHandler;



    //TODO: Database Socket
    //TODO: Connection Class
    //TODO: Query Class
    //TODO: Push Update Class
    //TODO: GUI
    //TODO: Ingredients Page
    //TODO: Recipe Page
    //TODO: Shopping List
    //TODO: Pop-up Tutorial


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        dbHandler = new DBHandler(MainActivity.this);


        com.example.wfd.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Wire up the Recipes button to do stuff
        //..get the Recipes button
        ImageButton btnRecipes = findViewById(R.id.btnRecipes);
        //..set what happens when the user clicks on Recipes - will do more things when things are coded
        btnRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecipeActivity.class);
            startActivity(intent);
        });


        //Wire up the My Pantry button to do stuff
        //..get the My Pantry button
        ImageButton btnPantry = findViewById(R.id.btnPantry);
        //..set what happens when the user clicks on My Pantry - will do more things when things are coded
        btnPantry.setOnClickListener(v -> {
            Intent intent = new Intent(this, Pantry.class);
            startActivity(intent);
        });

        //Wire up the Shopping List button to do stuff
        //..get the Shopping List button
        ImageButton btnShoppingList = findViewById(R.id.btnShoppingList);

        //..set what happens when the user clicks on Shopping List - will do more things when things are coded
//        btnShoppingList.setOnClickListener(v -> {
//            Intent intent = new Intent(this, Shopping.class);
//            startActivity(intent);
//
//        });
        btnShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "This shows my shopping list.");
                Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        //Wire up the My Account button to do stuff
        //..get the My Account button
        ImageButton btnMyAccount = findViewById(R.id.btnMyAccount);
        //..set what happens when the user clicks on My Account - will do more things when things are coded
        btnMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "This shows My Account.");
                Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT)
                        .show();
            }
        });

//        //Wire up the Tutorial button to do stuff
//        //..get the Tutorial button
//        Button btnTutorial = findViewById(R.id.btnTutorial);
//        //..set what happens when the user clicks on the Tutorial - will do more things when things are coded
//        btnTutorial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i(TAG, "This shows the Tutorial.");
//                Toast.makeText(getApplicationContext(), "Tutorial", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
//
//        //Wire up the Camera button to do stuff
//        //..get the Camera button
//        ImageButton btnCamera = findViewById(R.id.btnCamera);
//        //..set what happens when the user clicks on the Camera - will do more things when things are coded
//        btnCamera.setOnClickListener(v -> {
//            Intent intent = new Intent(this, CameraActivity.class);
//            startActivity(intent);
//
//        });

    }
}
