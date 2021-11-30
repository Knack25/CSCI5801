package com.example.wfd;

import android.content.Context;
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
import DB.Objects.Recipe;


public class ShoppingAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Recipe> list = new ArrayList<Recipe>();
    private Context context;



    public ShoppingAdapter(ArrayList<Recipe> list, Context context) {
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
            view = inflater.inflate(R.layout.pantry_list_item_layout, null);
        }

        //Handle TextView
        TextView recipeText = (TextView)view.findViewById(R.id.recipeText);
      //  recipeText.setText(list.get(position).getName());

        TextView ingredientOne = (TextView)view.findViewById(R.id.shopIngredientOne);
        //set textview
        TextView ingredientTwo = (TextView) view.findViewById(R.id.shopIngredientTwo);
        //set textview
        TextView ingredientThree = (TextView) view.findViewById(R.id.shopIngredientThree);
        //set textview

        TextView shopAmountOne = (TextView) view.findViewById(R.id.shopAmountOne);
        //set textview
        TextView shopAmountTwo = (TextView) view.findViewById(R.id.shopAmountTwo);
        //set textview
        TextView shopAmountThree = (TextView) view.findViewById(R.id.shopAmountThree);
        //set textview



        return view;
    }


}

