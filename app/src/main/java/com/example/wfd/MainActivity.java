package com.example.wfd;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.wfd.databinding.ActivityMainBinding;
import com.example.wfd.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;


import DB.Objects.Ingredient;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.SyncConfiguration;

public class MainActivity extends AppCompatActivity {

    public static AtomicReference<User> user = new AtomicReference<User>();
    public static App app;
    private static final AtomicBoolean success = new AtomicBoolean(false);
    private static Ingredient testingredient;






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

        com.example.wfd.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        //ViewPager viewPager = binding.viewPager;
        //viewPager.setAdapter(sectionsPagerAdapter);
        //TabLayout tabs = binding.tabs;
        //tabs.setupWithViewPager(viewPager);
        //FloatingActionButton fab = binding.fab;

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Realm.init(this);
        String appID = "wfd-ppxod";
        app = new App(new AppConfiguration.Builder(appID).build());

        Credentials emailPasswordCredientals = Credentials.emailPassword(
                "nathangschneider@gmail.com", "Test123");


        app.loginAsync(emailPasswordCredientals,it -> {
            if(it.isSuccess()){
                Log.v("AUTH","Successfully authenticated using an email and password");
                Log.v("AUTH","Logged in user ID: " + app.currentUser().getId());
                user.set(app.currentUser());
                Log.d("DEBUG","User ID " + user.get().getId());
                success.set(true);
            } else {
                Log.e("AUTH" , it.getError().toString());
                success.set(false);
            }
        });

        if(!success.get()){
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

//                testingredient = new Ingredient("Flour", 1345546754,10,
//                        "lbs",app.currentUser().getId());

//                realm.executeTransaction(r -> {
//                    r.insert(testingredient);
//                });

                RealmResults<Ingredient> ingredients = realm.where(Ingredient.class).findAll();

                Log.d("DEBUG",ingredients.toString());
                realm.close();
                success.set(true);
            }
        });


    }
}