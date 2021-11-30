package com.example.wfd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

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
            view = inflater.inflate(R.layout.pantry_list_item_layout, null);
        }

       //inflate list with recipe names and make the names clickable
        //clicking on a recipe name will open a new fragment or activity with the details of the recipe clicked.

        return view;
    }

}
