package com.example.wfd.recipe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.wfd.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.ArrayList;

import DB.Objects.Ingredient;


public class RecipeDetailAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Ingredient> list;
    private Context context;



    public RecipeDetailAdapter(ArrayList<Ingredient> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.recipe_detail_list_item_layout, null);
        }

        Log.v("DEBUG","List has a size of: " + list.size());
        //Handle TextView and display string from your list
        TextView ingredientName = (TextView)view.findViewById(R.id.recipeDetailingredientName);
        ingredientName.setText(list.get(position).getName());

        TextView amountOfIngredient = (TextView)view.findViewById(R.id.recipeDetailamountOfIngredient);

        amountOfIngredient.setText(String.valueOf(list.get(position).getAmmount()));


        TextView amountType = (TextView) view.findViewById(R.id.recipeDetailAmountType);

        if (list.get(position).getAmmount() > 1){
            amountType.setText(list.get(position).getAmmount_type() + "S");
        }else{
            amountType.setText(list.get(position).getAmmount_type());
        }






        return view;
    }


}

