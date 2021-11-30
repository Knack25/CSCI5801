package com.example.wfd;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wfd.ui.main.SectionsPagerAdapter;
import com.example.wfd.databinding.ActivityMainBinding;

import DB.DBHandler;
import io.realm.mongodb.App;


public class MainActivity extends AppCompatActivity {

   // public static AtomicReference<User> user = new AtomicReference<User>();
    public static App app;
    //private static final AtomicBoolean success = new AtomicBoolean(false);
    //private static Ingredient testingredient;

    private static final String TAG = "WFD";

    private DBHandler dbHandler;


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

    //    dbHandler = new DBHandler(MainActivity.this);


        com.example.wfd.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

/*
        Realm.init(this);
        String appID = "wfd-ppxod";
        app = new App(new AppConfiguration.Builder(appID).build());

        Credentials emailPasswordCredientals = Credentials.emailPassword(
                "nathangschneider@gmail.com", "Test123");


        app.loginAsync(emailPasswordCredientals, it -> {
            if (it.isSuccess()) {
                Log.v("AUTH", "Successfully authenticated using an email and password");
                Log.v("AUTH", "Logged in user ID: " + app.currentUser().getId());
                user.set(app.currentUser());
                Log.d("DEBUG", "User ID " + user.get().getId());
                success.set(true);
            } else {
                Log.e("AUTH", it.getError().toString());
                success.set(false);
            }
        });

        if (!success.get()) {
            //TODO:  Redirect to login page
        }

        SyncConfiguration config = new SyncConfiguration.Builder(app.currentUser(), app.currentUser().getId())
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();

        Realm.getInstanceAsync(config, new Realm.Callback() {
            @Override
            public void onSuccess(Realm realm) {
                Log.v(
                        "EXAMPLE",
                        "Successfully opened a realm with reads and writes allowed on the UI thread."
                );

                realm.executeTransaction(r -> {
                    Ingredient testingredient = r.createObject(Ingredient.class,new ObjectId());
                    testingredient.setName("Flour");
                    testingredient.setUpc(1345546754);
                    testingredient.setAmmount(10);
                    testingredient.setAmmount_type("lbs");
                    r.insert(testingredient);
                });

                RealmResults<Ingredient> ingredients = realm.where(Ingredient.class).findAll();

                Log.d("DEBUG", ingredients.toString());
                realm.close();
                success.set(true);
            }
        });
*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Wire up the Recipes button to do stuff
        //..get the Recipes button
        Button btnRecipes = findViewById(R.id.btnRecipes);
        //..set what happens when the user clicks on Recipes - will do more things when things are coded
        btnRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "This shows Recipes.");
                Toast.makeText(getApplicationContext(), "Recipes", Toast.LENGTH_SHORT)
                        .show();
            }
        });


        //Wire up the My Pantry button to do stuff
        //..get the My Pantry button
        Button btnPantry = findViewById(R.id.btnPantry);
        //..set what happens when the user clicks on My Pantry - will do more things when things are coded
        btnPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "This shows My Pantry.");
                Toast.makeText(getApplicationContext(), "My Pantry", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        //Wire up the Shopping List button to do stuff
        //..get the Shopping List button
        Button btnShoppingList = findViewById(R.id.btnShoppingList);
        //..set what happens when the user clicks on Shopping List - will do more things when things are coded
        btnShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "This shows the Shopping List.");
                Toast.makeText(getApplicationContext(), "Shopping List", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        //Wire up the My Account button to do stuff
        //..get the My Account button
        Button btnMyAccount = findViewById(R.id.btnMyAccount);
        //..set what happens when the user clicks on My Account - will do more things when things are coded
        btnMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "This shows My Account.");
                Toast.makeText(getApplicationContext(), "My Account", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        //Wire up the Tutorial button to do stuff
        //..get the Tutorial button
        Button btnTutorial = findViewById(R.id.btnTutorial);
        //..set what happens when the user clicks on the Tutorial - will do more things when things are coded
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "This shows the Tutorial.");
                Toast.makeText(getApplicationContext(), "Tutorial", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        //Wire up the Camera button to do stuff
        //..get the Camera button
        Button btnCamera = findViewById(R.id.btnCamera);
        //..set what happens when the user clicks on the Camera - will do more things when things are coded
        btnCamera.setOnClickListener(v -> {
//            @Override
//            public void onClick(View view) {
//                Log.i(TAG, "This shows the Camera.");
//                Toast.makeText(getApplicationContext(), "Camera", Toast.LENGTH_SHORT)
//                        .show();
//            }
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);

        });

    }
}
