package com.example.wfd.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.wfd.R;

import java.util.ArrayList;

import DB.Objects.Recipe;

public class RecipeAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Recipe> list = new ArrayList<Recipe>();
    private Context context;


    public RecipeAdapter(ArrayList<Recipe> list, Context context) {
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
            view = inflater.inflate(R.layout.recipe_list_item_layout, null);
        }


        //Handle TextView and display string from your list
        TextView recipeName = (TextView)view.findViewById(R.id.recipeName);
        recipeName.setText(list.get(position).getName());


        return view;
    }
}
