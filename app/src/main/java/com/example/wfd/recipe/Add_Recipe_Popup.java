package com.example.wfd.recipe;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wfd.MainActivity;
import com.example.wfd.R;

public class Add_Recipe_Popup extends AppCompatActivity {

    LayoutInflater inflater;
    View popupView;
    int width;
    int height;
    boolean focusable = true;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_popup);

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.activity_add_recipe_popup,null);
        width = LinearLayout.LayoutParams.WRAP_CONTENT;
        height = LinearLayout.LayoutParams.WRAP_CONTENT;
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        EditText name = (EditText) findViewById(R.id.newrecipeName);
        EditText description = (EditText) findViewById(R.id.newrecipeDescription);
        EditText steps = (EditText) findViewById(R.id.newrecipeSteps);
        Button addrecipe = (Button) findViewById(R.id.newrecipeButton);



        addrecipe.setOnClickListener(v -> {
            MainActivity.dbHandler.createRecipe(String.valueOf(name.getText()),String.valueOf(description.getText()),
                    String.valueOf(steps.getText()));

            Log.v("DEBUG","Adding Recipe to DB");
            popupWindow.dismiss();
        });


    }

    public void addIngredientPopup(View view){






        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }

        });

    }

}
