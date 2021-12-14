package com.example.wfd.pantry;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wfd.CameraActivity;
import com.example.wfd.MainActivity;
import com.example.wfd.R;

public class Add_Ingredient_Popup extends AppCompatActivity {

    LayoutInflater inflater;
    View popupView;
    int width;
    int height;
    boolean focusable = true;
    PopupWindow popupWindow;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient_popup);
        intent = getIntent();


        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.activity_add_ingredient_popup,null);
        width = LinearLayout.LayoutParams.WRAP_CONTENT;
        height = LinearLayout.LayoutParams.WRAP_CONTENT;
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Grab elements from layout
        EditText name = (EditText) findViewById(R.id.newingredientName);
        EditText amount = (EditText) findViewById(R.id.newingredientamount);
        Spinner type = (Spinner) findViewById(R.id.newingredienttype);
        EditText upc = (EditText) findViewById(R.id.newingredientUPC);
        Button addingredient = (Button) findViewById(R.id.newingredientbutton);

       //If intent from camera exists, set the current variables to whatever the intent extras are.

          if(intent.getStringExtra("NAME") != null) name.setText(intent.getStringExtra("NAME"));

        //since Type is held in a spinner, switch statement is used to set the value coming back from camera.
        String intentType = intent.getStringExtra("TYPE");

        if(intentType != null) {
            switch (intentType) {
                case "CUP":
                    type.setSelection(0);
                case "TEASPOON":
                    type.setSelection(1);
                case "TABLESPOON":
                    type.setSelection(2);
                case "UNIT":
                    type.setSelection(3);
                default:
                    break;
            }
        }
        if(intent.getStringExtra("AMOUNT") != null) amount.setText(intent.getStringExtra("AMOUNT"));


        if(intent.getStringExtra("UPC") != null) upc.setText(intent.getStringExtra("UPC"));




        ImageButton pantryCamera = (ImageButton) findViewById(R.id.pantryCameraBtn);

        //set up data handling for the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.measurements, android.R.layout.simple_spinner_item);
        Log.v("DEBUG",adapter.toString());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);


        //If the pantryCamera Button is pressed
        pantryCamera.setOnClickListener(v -> {
                //Store the information present into the intent so it is not lost between activities.
                Intent intent = new Intent(this, CameraActivity.class);
                intent.putExtra("TYPE", String.valueOf(type.getSelectedItem()));
                intent.putExtra("AMOUNT", String.valueOf(amount.getText()));
                intent.putExtra("NAME", String.valueOf(name.getText()));

                startActivity(intent);

        });

        //If the add ingredient button is pressed
        addingredient.setOnClickListener(v -> {
            //Add ingredient to DB
            MainActivity.dbHandler.addIngredient(String.valueOf(name.getText()),String.valueOf(upc.getText()),
                    Integer.valueOf(String.valueOf(amount.getText())),String.valueOf(type.getSelectedItem()));

            Log.v("DEBUG","Adding Ingredient to DB");
            //Return to pantry
            Intent intent = new Intent(this, Pantry.class);
            startActivity(intent);
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
