package DB;

import static com.example.wfd.MainActivity.app;
import static com.example.wfd.MainActivity.user;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import DB.Objects.Ingredient;
import DB.Objects.Task;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.sync.SyncConfiguration;


public class Socket {



    public static boolean logout(){
        AtomicBoolean success = new AtomicBoolean(false);
        user.get().logOutAsync( result -> {
            if (result.isSuccess()) {
                Log.v("AUTH", "Successfully logged out.");
                success.set(true);
            } else {
                Log.e("AUTH", result.getError().toString());
                success.set(false);
            }
        });

        return success.get();
    }

    public static boolean getRealm(){
        AtomicBoolean success = new AtomicBoolean(false);
        Log.d("DEBUG","Passed User ID: " + user.get().getId());
        SyncConfiguration config = new SyncConfiguration.Builder(app.currentUser(), user.get().getId())
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
                RealmResults<Task> tasks = realm.where(Task.class).findAll();

                realm.executeTransaction(r -> {
                    r.insert(new Task());
                });

                realm.close();
                success.set(true);
            }
        });
        return success.get();
    }

    /*
    private static App app;
    private static AtomicReference<User> user;
    private static Credentials credentials;

    private static String appID = "wfd-ppxod";
    private static String email = "nathangschneider@gmail.com";
    private static String password = "Test123";

    private static MongoDatabase mongoDatabase;
    private static MongoClient mongoClient;
    private static MongoCollection<Document> ingredients;
    private static MongoCollection<Document> recipes;



    public static App init(){
        try {

            app = new App(new AppConfiguration.Builder(appID).build());

            credentials =
                    Credentials.emailPassword(email,password);

            user = new AtomicReference<User>();



            if(!login()){
                Log.e("AUTH","Failed to login... Attempting to register user");
                if(!register()){
                    Log.e("AUTH","Failed to register user");
                }

            }




            return app;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean login(){
        try{
            app.loginAsync(credentials, it -> {

                if (it.isSuccess()){
                    Log.v("AUTH","Successfully authenticated using " + email);
                    user.set(app.currentUser());
                }
                else {
                    Log.e("AUTH",it.getError().toString());
                }

            });
            return true;
        }catch (Exception login){
            login.printStackTrace();
            return false;
        }
    }

    public static boolean register(){

        try{
            app.getEmailPassword().registerUserAsync(email,password,it ->{
                if (it.isSuccess()) {
                    Log.v("AUTH", "Successfully Registered Uers");
                } else {
                    Log.e("AUTH", "Failed to register user: " + it.getError().toString());
                }
            });
            return true;
        }catch (Exception registerE){
            registerE.printStackTrace();
            return false;
        }
    }

    public void logout() {
        user.get().logOutAsync(result -> {
            if (result.isSuccess()) {
                Log.v("AUTH", "Successfully Logged out");
            } else {
                Log.e("AUTH", result.getError().toString());
            }
        });
    }

    public static void insert(Ingredient ingredient){
        mongoClient = user.get().getMongoClient("mongodb-atlas");
        mongoDatabase = mongoClient.getDatabase("WFD");
        ingredients = mongoDatabase.getCollection("Ingredients");

        ingredients.insertOne(new Document(
                "userid",user.get().getId()).append("data",ingredient)).getAsync(result -> {
                    if(result.isSuccess()){
                        Log.v("Data","Ingredient Successfully Inserted");
                    } else{
                        Log.v("Data","Error: " + result.getError().toString());
                    }
        });
    }
    public void insert(Recipe recipe){
        mongoClient = user.get().getMongoClient("mongodb-atlas");
        mongoDatabase = mongoClient.getDatabase("WFD");
        recipes = mongoDatabase.getCollection("Recipes");

        recipes.insertOne(new Document(
                "userid",user.get().getId()).append("data",recipe)).getAsync(result -> {
            if(result.isSuccess()){
                Log.v("Data","Ingredient Successfully Inserted");
            } else{
                Log.v("Data","Error: " + result.getError().toString());
            }
        });
    }
*/
}
