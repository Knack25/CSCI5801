package DB;

import android.util.Log;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class Socket {

    private static String appID = "wfd-ppxod";

    public static boolean init(){
        try {

            App app = new App(new AppConfiguration.Builder(appID).build());

            Credentials credentials =
                    Credentials.emailPassword("nathangschneider@gmail.com","Test123");

            app.loginAsync(credentials,new App.Callback<User>(){

                @Override
                public void onResult(App.Result<User> result) {
                    if (result.isSuccess()){
                        Log.v("User","Logged In Successfully");
                    }else {
                        Log.v("User","Failed to Login");
                    }
                }
            });
            app.getEmailPassword().registerUserAsync("nathangschneider@gmail.com","Test",it->{
                if(it.isSuccess()){
                    Log.v("User","Registered with Email Successfully");
                }else{
                    Log.v("User","Registration Failed");
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
